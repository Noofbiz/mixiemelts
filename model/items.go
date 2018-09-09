package model

import (
	"encoding/base64"
	"encoding/json"
	"log"
	"net/http"
	"time"
)

type Item struct {
	ItemName    string
	ID          string
	Qty         int
	PriceWeight int
}

func ItemJar(r *http.Request) (i []Item) {
	c, err := r.Cookie("items")
	if err != nil {
		log.Println(err.Error())
		return
	}
	s, _ := base64.StdEncoding.DecodeString(c.Value)
	json.Unmarshal(s, &i)
	return
}

func AddItem(w http.ResponseWriter, r *http.Request, n string, q, weight int) {
	i := ItemJar(r)
	i = append(i, Item{n, ItemID(), q, weight})
	UpdateItemJar(w, i)
}

func RemoveItem(w http.ResponseWriter, r *http.Request, id string) {
	i := ItemJar(r)
	for j := 0; j < len(i); j++ {
		if i[j].ID == id {
			remove(j, &i)
		}
	}
	UpdateItemJar(w, i)
}

func UpdateItem(w http.ResponseWriter, r *http.Request, id string, q int) {
	i := ItemJar(r)
	log.Printf("qty to update to is: %v", q)
	for j := 0; j < len(i); j++ {
		if i[j].ID == id {
			log.Printf("id matches. Here and qty is: %v", i[j].Qty)
			i[j].Qty = q
			log.Printf("after update and qty is: %v", i[j].Qty)
		}
	}
	UpdateItemJar(w, i)
}

func remove(j int, i *[]Item) {
	items := *i
	items = append(items[:j], items[j+1:]...)
	*i = items
}

func UpdateItemJar(w http.ResponseWriter, i []Item) {
	v, _ := json.Marshal(i)
	c := http.Cookie{
		Name:    "items",
		Value:   base64.StdEncoding.EncodeToString(v),
		Expires: time.Now().Add(365 * 24 * time.Hour),
		Path:    "/",
	}
	http.SetCookie(w, &c)
}

func ItemID() string {
	return "eyediddlydee" + time.Now().Format("20060102150405")
}
