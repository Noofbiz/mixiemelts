package model

import (
	"golang.org/x/net/context"

	"google.golang.org/appengine/user"
)

type Mixer struct {
	Email string
	ID    string
	Admin bool
	URL   string
}

func MixerStatus(ctx context.Context) Mixer {
	u := user.Current(ctx)
	if u == nil {
		url, _ := user.LoginURL(ctx, "/")
		return Mixer{"", "", false, url}
	}
	url, _ := user.LogoutURL(ctx, "/")
	return Mixer{u.Email, u.ID, u.Admin, url}
}
