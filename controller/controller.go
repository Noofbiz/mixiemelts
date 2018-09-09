package controller

import (
	"html/template"
	"net/http"
)

var funcMap = template.FuncMap{
	"mod": func(i, j int) bool {
		return i%j == 0
	},
	"inc": func(i int) int {
		return i + 1
	},
	"multiply": func(i float64, j int) float64 {
		return i * float64(j)
	},
}

var templates = template.Must(template.New("main").Funcs(funcMap).ParseGlob("view/HTML/*"))

func MainHandler(w http.ResponseWriter, r *http.Request) {
	d := NavBarInfo(r)

	err := templates.ExecuteTemplate(w, "indexPage", d)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
}

func AboutHandler(w http.ResponseWriter, r *http.Request) {
	d := NavBarInfo(r)

	err := templates.ExecuteTemplate(w, "about", d)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
}
