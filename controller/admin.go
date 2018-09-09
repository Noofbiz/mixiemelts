package controller

import (
	"model"
	"net/http"
	"strconv"
	"strings"

	"google.golang.org/appengine"
	"google.golang.org/appengine/log"
)

type AdminData struct {
	User      model.Mixer
	Cart      model.ShoppingCart
	Orders    []model.Order
	Inventory []model.Inventory
	Scents    []model.Scent
	Tags      []string
}

func AdminInfo(r *http.Request, s string) AdminData {
	nav := NavBarInfo(r)
	var orders []model.Order
	var inventory []model.Inventory
	var scents []model.Scent

	if strings.Contains(s, "o") {
		orders = model.OrdersPlaced(r)
	}

	if strings.Contains(s, "i") {
		inventory = model.InventoryAvailable(r)
	}

	if strings.Contains(s, "s") {
		scents = model.ScentsAvailable(r)
	}

	return AdminData{nav.User, nav.Cart, orders, inventory, scents, model.PossibleTags}
}

func AdminHandler(w http.ResponseWriter, r *http.Request) {
	d := AdminInfo(r, "")
	if d.User.Admin {
		err := templates.ExecuteTemplate(w, "admin", d)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
	} else {
		http.NotFound(w, r)
		return
	}
}

func AdminOrderHandler(w http.ResponseWriter, r *http.Request) {
	d := AdminInfo(r, "o")
	if d.User.Admin {
		err := templates.ExecuteTemplate(w, "admin_orders", d)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
	} else {
		http.NotFound(w, r)
		return
	}
}

func AdminOrderPlaceUpdateHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	ctx := appengine.NewContext(r)

	p, _ := strconv.ParseBool(r.PostFormValue("Paid"))
	m, _ := strconv.ParseBool(r.PostFormValue("Made"))
	s, _ := strconv.ParseBool(r.PostFormValue("Sent"))
	order := model.Order{
		Item:     model.ItemJar(r),
		Paid:     p,
		Made:     m,
		Email:    r.PostFormValue("Email"),
		Name:     r.PostFormValue("Name"),
		Address:  r.PostFormValue("Address"),
		Sent:     s,
		Tracking: r.PostFormValue("Tracking"),
	}

	if place, _ := strconv.ParseBool(r.PostFormValue("Place")); place {
		if ok := model.PlaceOrder(order, r); !ok {
			log.Errorf(ctx, "Failed to place order.")
		}
	} else {
		num := r.PostFormValue("OrderNum")
		p2, _ := strconv.ParseBool(r.PostFormValue("Paid2"))
		m2, _ := strconv.ParseBool(r.PostFormValue("Made2"))
		s2, _ := strconv.ParseBool(r.PostFormValue("Sent2"))
		u := model.Order{
			Item:     order.Item,
			Paid:     p2,
			Made:     m2,
			Email:    r.PostFormValue("Email2"),
			Name:     r.PostFormValue("Name2"),
			Address:  r.PostFormValue("Address2"),
			Sent:     s2,
			Tracking: r.PostFormValue("Tracking2"),
			OrderNum: r.PostFormValue("OrderNum"),
		}
		if ok := model.UpdateOrder(num, u, r); !ok {
			log.Errorf(ctx, "Failed to update order")
		}
	}

	http.Redirect(w, r, "/Admin/Orders", http.StatusSeeOther)
}

func AdminDeleteOrderHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	ctx := appengine.NewContext(r)

	if ok := model.DeleteOrder(r.FormValue("OrderNum"), r); !ok {
		log.Errorf(ctx, "Failed to update order")
	}

	http.Redirect(w, r, "/Admin/Orders", http.StatusSeeOther)
}

func AdminSSHandler(w http.ResponseWriter, r *http.Request) {
	d := AdminInfo(r, "")
	if d.User.Admin {
		err := templates.ExecuteTemplate(w, "admin_sticker_sheet", d)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
	} else {
		http.NotFound(w, r)
		return
	}
}

func AdminInventoryHandler(w http.ResponseWriter, r *http.Request) {
	d := AdminInfo(r, "i")
	if d.User.Admin {
		err := templates.ExecuteTemplate(w, "admin_inventory", d)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
	} else {
		http.NotFound(w, r)
		return
	}
}

func AdminInventoryAddUpdateHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	ctx := appengine.NewContext(r)

	q, _ := strconv.Atoi(r.PostFormValue("Qty"))
	inv := model.Inventory{
		Item: r.PostFormValue("Item"),
		Qty:  q,
	}

	if place, _ := strconv.ParseBool(r.PostFormValue("Place")); place {
		if ok := model.AddInventory(inv, r); !ok {
			log.Errorf(ctx, "Failed to add to inventory.")
		}
	} else {
		q2, _ := strconv.Atoi(r.PostFormValue("Qty2"))
		inv2 := model.Inventory{
			Item: r.PostFormValue("Item2"),
			Qty:  q2,
		}
		if ok := model.UpdateInventory(inv, inv2, r); !ok {
			log.Errorf(ctx, "Failed to update inventory")
		}
	}

	http.Redirect(w, r, "/Admin/Inventory", http.StatusSeeOther)
}

func AdminInventoryDeleteHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	ctx := appengine.NewContext(r)

	inv := model.Inventory{
		Item: r.FormValue("Item"),
		Qty:  0,
	}

	if ok := model.DeleteInventory(inv, r); !ok {
		log.Errorf(ctx, "Failed to delete inventory")
	}

	http.Redirect(w, r, "/Admin/Inventory", http.StatusSeeOther)
}

func AdminScentsHandler(w http.ResponseWriter, r *http.Request) {
	d := AdminInfo(r, "is")
	if d.User.Admin {
		err := templates.ExecuteTemplate(w, "admin_scents", d)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
	} else {
		http.NotFound(w, r)
		return
	}
}

func AdminScentAddUpdateHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	ctx := appengine.NewContext(r)

	tags := stringSplitter(r.PostFormValue("Tags"))
	recipe := getRecipe(r.PostFormValue("Recipe"))

	scent := model.Scent{
		Name:        r.PostFormValue("Name"),
		Tags:        tags,
		Description: r.PostFormValue("Description"),
		Recipe:      recipe,
	}

	if place, _ := strconv.ParseBool(r.PostFormValue("Place")); place {
		if ok := model.AddScent(scent, r); !ok {
			log.Errorf(ctx, "Failed to create scent.")
		}
	} else {
		tags2 := stringSplitter(r.PostFormValue("Tags2"))
		recipe2 := getRecipe(r.PostFormValue("Recipe2"))
		s2 := model.Scent{
			Name:        r.PostFormValue("Name2"),
			Tags:        tags2,
			Description: r.PostFormValue("Description2"),
			Recipe:      recipe2,
		}
		if ok := model.UpdateScent(scent, s2, r); !ok {
			log.Errorf(ctx, "Failed to update scent")
		}
	}

	http.Redirect(w, r, "/Admin/Scents", http.StatusSeeOther)
}

func AdminScentDeleteHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	ctx := appengine.NewContext(r)

	scent := model.Scent{
		Name: r.FormValue("Name"),
	}

	if ok := model.DeleteScent(scent, r); !ok {
		log.Errorf(ctx, "Failed to delete scent")
	}

	http.Redirect(w, r, "/Admin/Scents", http.StatusSeeOther)
}
