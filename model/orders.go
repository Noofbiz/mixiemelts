package model

import (
	"net/http"
	"strconv"
	"time"

	"google.golang.org/appengine"
	"google.golang.org/appengine/datastore"
	"google.golang.org/appengine/log"
)

type Order struct {
	Item     []Item
	OrderNum string
	Paid     bool
	Made     bool
	Email    string
	Name     string
	Address  string
	Sent     bool
	Tracking string
}

func OrdersPlaced(r *http.Request) []Order {
	var ret []Order

	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Order")
	t := q.Run(ctx)
	for {
		var o Order
		_, err := t.Next(&o)
		if err == datastore.Done {
			break // No further entities match the query.
		}
		if err != nil {
			log.Errorf(ctx, "fetching next Order: %v", err)
			break
		}

		ret = append(ret, o)
	}

	return ret
}

func PlaceOrder(o Order, r *http.Request) bool {
	ctx := appengine.NewContext(r)
	key := datastore.NewIncompleteKey(ctx, "Order", nil)

	o.OrderNum = strconv.FormatInt(time.Now().UnixNano(), 10)

	if _, err := datastore.Put(ctx, key, &o); err != nil {
		log.Errorf(ctx, "Entering Order: %v", err)
		return false
	}
	return true
}

func UpdateOrder(num string, o Order, r *http.Request) bool {
	ctx := appengine.NewContext(r)
	q := datastore.NewQuery("Order").
		Filter("OrderNum =", num).
		KeysOnly().
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No orders match! count is: %v \n Order: %v", c, o)
		return false
	}

	t := q.Run(ctx)

	k, _ := t.Next(nil)

	if _, err := datastore.Put(ctx, k, &o); err != nil {
		log.Errorf(ctx, "Updating Order: %v \n Order in: %v", err, o)
		return false
	}
	return true
}

func DeleteOrder(o string, r *http.Request) bool {
	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Order").
		Filter("OrderNum =", o).
		KeysOnly().
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No orders match! count is: %v \n Order: %v", c, o)
		return false
	}

	t := q.Run(ctx)

	k, _ := t.Next(nil)

	if err := datastore.Delete(ctx, k); err != nil {
		log.Errorf(ctx, "Deleting Order: %v", err)
		return false
	}
	return true
}
