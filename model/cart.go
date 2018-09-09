package model

import "net/http"

type ShoppingCart struct {
	ItemCount int
	Items     []Item
}

func Cart(r *http.Request) (c ShoppingCart) {
	c.Items = ItemJar(r)
	c.ItemCount = len(c.Items)
	return
}
