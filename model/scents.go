package model

import (
	"net/http"

	"google.golang.org/appengine"
	"google.golang.org/appengine/datastore"
	"google.golang.org/appengine/log"
)

type Scent struct {
	Name        string
	Tags        []string
	Description string
	Recipe      []Inventory
}

func ScentsAvailable(r *http.Request) []Scent {
	var ret []Scent

	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Scent")
	t := q.Run(ctx)
	for {
		var i Scent
		_, err := t.Next(&i)
		if err == datastore.Done {
			break // No further entities match the query.
		}
		if err != nil {
			log.Errorf(ctx, "fetching next Scent: %v", err)
			break
		}

		ret = append(ret, i)
	}

	return ret
}

func AddScent(s Scent, r *http.Request) bool {
	ctx := appengine.NewContext(r)
	key := datastore.NewIncompleteKey(ctx, "Scent", nil)

	if _, err := datastore.Put(ctx, key, &s); err != nil {
		log.Errorf(ctx, "Entering Order: %v", err)
		return false
	}
	return true
}

func UpdateScent(s1 Scent, s2 Scent, r *http.Request) bool {
	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Scent").
		Filter("Name =", s1.Name).
		KeysOnly().
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No scents match! count is: %v \n Scents: %v", c, s1)
		return false
	}

	t := q.Run(ctx)

	k, _ := t.Next(nil)

	if _, err := datastore.Put(ctx, k, &s2); err != nil {
		log.Errorf(ctx, "Updating Order: %v \n Scent in: %v", err, s2)
		return false
	}
	return true
}

func DeleteScent(s Scent, r *http.Request) bool {
	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Scent").
		Filter("Name =", s.Name).
		KeysOnly().
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No scents match! count is: %v \n scent: %v", c, s)
		return false
	}

	t := q.Run(ctx)

	k, _ := t.Next(nil)

	if err := datastore.Delete(ctx, k); err != nil {
		log.Errorf(ctx, "Deleting scent: %v", err)
		return false
	}
	return true
}

func ScentInfo(s string, r *http.Request) Scent {
	ctx := appengine.NewContext(r)

	q := datastore.NewQuery("Scent").
		Filter("Name =", s).
		Limit(2)

	if c, _ := q.Count(ctx); c != 1 {
		log.Errorf(ctx, "Multiple/No scents match! count is: %v \n scent: %v", c, s)
	}

	t := q.Run(ctx)

	var i Scent
	_, err := t.Next(&i)
	if err != nil {
		log.Errorf(ctx, "fetching next Scent: %v", err)
	}
	return i
}

func ScentTagMatches(s string, r *http.Request) []Scent {
	var ret []Scent

	for _, scent := range ScentsAvailable(r) {
		for _, tag := range scent.Tags {
			if tag == s {
				ret = append(ret, scent)
			}
		}
	}

	return ret
}
