package controller

import "net/http"

type paymentJSON struct {
	intent                string
	experience_profile_id string
	redirect_urls         redirURLs
	payer                 payerMethod
	transactions          []transaction
}

type transaction struct {
	amount         amount
	item_list      i_list
	description    string
	invoice_number string
	custom         string
}

type i_list struct {
	items []stuffs
}

type stuffs struct {
	quantity    string
	name        string
	price       string
	currency    string
	description string
	tax         string
}

type amount struct {
	total    string
	currency string
	details  details
}

type details struct {
	subtotal          string
	shipping          string
	tax               string
	shipping_discount string
}

type payerMethod struct {
	payment_method string
}

type redirURLs struct {
	return_url string
	cancel_url string
}

func PaypalPaymentHandler(w http.ResponseWriter, r *http.Request) {
	d := NavBarInfo(r)

	err := templates.ExecuteTemplate(w, "indexPage", d)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
}
