package controller

import (
	"model"
	"strconv"

	"net/http"
)

type CartPageData struct {
	User      model.Mixer
	Cart      model.ShoppingCart
	PriceMult float64
	SubTotal  float64
	Shipping  float64
	Total     float64
}

func ShoppingCartHandler(w http.ResponseWriter, r *http.Request) {
	nav := NavBarInfo(r)

	totalWeight := 0
	for _, item := range nav.Cart.Items {
		totalWeight += item.PriceWeight * item.Qty
	}

	price := 1.50
	shipping := 4.00
	if totalWeight >= 30 {
		price = 1.00
		shipping = 7.00
	} else if totalWeight >= 20 {
		price = 1.25
		shipping = 6.00
	} else if totalWeight >= 10 {
		shipping = 5.00
	}

	subtotal := 0.00
	for _, item := range nav.Cart.Items {
		subtotal += price * float64(item.Qty)
	}

	d := CartPageData{
		nav.User,
		nav.Cart,
		price,
		subtotal,
		shipping,
		subtotal + shipping,
	}

	err := templates.ExecuteTemplate(w, "ShoppingCart", d)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
}

func AddToCartHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	itemName := r.PostFormValue("Name")
	q, _ := strconv.Atoi(r.PostFormValue("Qty"))
	weight, _ := strconv.Atoi(r.PostFormValue("Weight"))

	model.AddItem(w, r, itemName, q, weight)
	http.Redirect(w, r, "/Cart", http.StatusSeeOther)
}

func RemoveFromCartHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	id := r.PostFormValue("id")
	model.RemoveItem(w, r, id)
	http.Redirect(w, r, "/Cart", http.StatusSeeOther)
}

func UpdateQtyHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	id := r.PostFormValue("id")
	q, _ := strconv.Atoi(r.PostFormValue("Qty"))
	model.UpdateItem(w, r, id, q)
	http.Redirect(w, r, "/Cart", http.StatusSeeOther)
}
