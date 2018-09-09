package controller

import (
	"model"

	"google.golang.org/appengine"

	"net/http"
)

type NavBarData struct {
	User model.Mixer
	Cart model.ShoppingCart
}

func NavBarInfo(r *http.Request) NavBarData {
	ctx := appengine.NewContext(r)

	u := model.MixerStatus(ctx)

	c := model.Cart(r)

	return NavBarData{u, c}
}
