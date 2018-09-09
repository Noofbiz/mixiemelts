package hello

import (
	"controller"
	"net/http"

	"github.com/gorilla/mux"
)

func init() {
	r := mux.NewRouter()
	r.HandleFunc("/", controller.MainHandler)
	r.HandleFunc("/Cart", controller.ShoppingCartHandler)
	r.HandleFunc("/AddToCart", controller.AddToCartHandler)
	r.HandleFunc("/RemoveFromCart", controller.RemoveFromCartHandler)
	r.HandleFunc("/UpdateQtyCart", controller.UpdateQtyHandler)
	r.HandleFunc("/Admin", controller.AdminHandler)
	r.HandleFunc("/Admin/Orders", controller.AdminOrderHandler)
	r.HandleFunc("/Admin/Orders/PlaceUpdateOrder", controller.AdminOrderPlaceUpdateHandler)
	r.HandleFunc("/Admin/Orders/DeleteOrder", controller.AdminDeleteOrderHandler)
	r.HandleFunc("/Admin/StickerSheets", controller.AdminSSHandler)
	r.HandleFunc("/Admin/Inventory", controller.AdminInventoryHandler)
	r.HandleFunc("/Admin/Inventory/AddUpdateInventory", controller.AdminInventoryAddUpdateHandler)
	r.HandleFunc("/Admin/Inventory/DeleteInventory", controller.AdminInventoryDeleteHandler)
	r.HandleFunc("/Admin/Scents", controller.AdminScentsHandler)
	r.HandleFunc("/Admin/Scents/AddUpdateScent", controller.AdminScentAddUpdateHandler)
	r.HandleFunc("/Admin/Scents/DeleteScent", controller.AdminScentDeleteHandler)
	r.HandleFunc("/About", controller.AboutHandler)
	r.HandleFunc("/Store", controller.StoreHandler)
	r.HandleFunc("/Paypal/CreatePayment", controller.PaypalPaymentHandler)
	http.Handle("/", r)
	http.Handle("/Cart", r)
	http.Handle("/Admin", r)
	http.Handle("/Admin/Orders", r)
	http.Handle("/Admin/StickerSheets", r)
	http.Handle("/Admin/Inventory", r)
	http.Handle("/Admin/Scents", r)
	http.Handle("/About", r)
	http.Handle("/Store", r)
	http.Handle("/static/", http.StripPrefix("/static/", http.FileServer(http.Dir("./view/static"))))
}
