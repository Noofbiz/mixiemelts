package controller

import (
	"model"
	"net/http"
)

type StoreData struct {
	User   model.Mixer
	Cart   model.ShoppingCart
	Scents []model.Scent
	Scent  model.Scent
	Tags   []string
}

func StoreHandler(w http.ResponseWriter, r *http.Request) {
	nav := NavBarInfo(r)
	r.ParseForm()

	var d StoreData
	d.User = nav.User
	d.Cart = nav.Cart

	t := r.FormValue("Tag")
	s := r.FormValue("Scent")

	if s != "" {
		d.Scent = model.ScentInfo(s, r)
	} else if t != "" {
		d.Scents = model.ScentTagMatches(t, r)
	} else {
		d.Tags = model.PossibleTags
	}

	err := templates.ExecuteTemplate(w, "store", d)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
}
