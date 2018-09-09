package model

import (
	"net/http"

	"google.golang.org/appengine"
	"google.golang.org/appengine/datastore"
	"google.golang.org/appengine/log"
)

type Inventory struct {
	Item string //physical item, like soy wax
	Qty  int    //number of wax melts that can be made from amout of physical item
}

func InventoryAvailable(r *http.Request) []Inventory {
	var ret []Inventory

	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Inventory")
	t := q.Run(ctx)
	for {
		var i Inventory
		_, err := t.Next(&i)
		if err == datastore.Done {
			break // No further entities match the query.
		}
		if err != nil {
			log.Errorf(ctx, "fetching next Product: %v", err)
			break
		}

		ret = append(ret, i)
	}

	return ret
}

func AddInventory(i Inventory, r *http.Request) bool {
	ctx := appengine.NewContext(r)
	key := datastore.NewIncompleteKey(ctx, "Inventory", nil)

	if _, err := datastore.Put(ctx, key, &i); err != nil {
		log.Errorf(ctx, "Entering Order: %v", err)
		return false
	}
	return true
}

func UpdateInventory(i1 Inventory, i2 Inventory, r *http.Request) bool {
	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Inventory").
		Filter("Item =", i1.Item).
		KeysOnly().
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No scents match! count is: %v \n Scents: %v", c, i1)
		return false
	}

	t := q.Run(ctx)

	k, _ := t.Next(nil)

	if _, err := datastore.Put(ctx, k, &i2); err != nil {
		log.Errorf(ctx, "Updating Order: %v \n Scent in: %v", err, i2)
		return false
	}
	return true
}

func DeleteInventory(i Inventory, r *http.Request) bool {
	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Inventory").
		Filter("Item =", i.Item).
		KeysOnly().
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No inv match! count is: %v \n inv: %v", c, i)
		return false
	}

	t := q.Run(ctx)

	k, _ := t.Next(nil)

	if err := datastore.Delete(ctx, k); err != nil {
		log.Errorf(ctx, "Deleting inv: %v", err)
		return false
	}
	return true
}
