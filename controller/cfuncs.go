package controller

import (
	"html/template"
	"model"
	"os"
	"path/filepath"
	"strconv"
	"strings"
)

func templateParse(name string, s []string) *template.Template {
	cwd, _ := os.Getwd()
	var cwds []string

	for _, ele := range s {
		cwds = append(cwds, filepath.Join(cwd, ele))
	}

	return template.Must(template.New(name).ParseFiles(cwds...))
}

func stringSplitter(s string) []string {
	sep := "#NEXT#%"
	return strings.Split(s, sep)
}

func getRecipe(s string) []model.Inventory {
	var ret []model.Inventory
	sep := "%#ItemQty#%"

	for _, v := range stringSplitter(s) {
		split := strings.Split(v, sep)
		q, _ := strconv.Atoi(split[1])
		add := model.Inventory{
			Item: split[0],
			Qty:  q,
		}
		ret = append(ret, add)
	}

	return ret
}
