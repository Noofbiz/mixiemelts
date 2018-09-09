{{define "navbar"}}
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
        <span class="sr-only">Toggle Navbar</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">
        <img alt="MixieMelts Logo" src="/static/images/MMLogo.svg" class="img-responsive">
      </a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="/About">About</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Shop <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/Store">Meet the scents</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/Store/Boxes">Subscription Boxes</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/Stickers">Get a sticker sheet!</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        {{if .User.Admin}}
        <li><a href="/Admin">Admin</a></li>
        {{end}}
        {{if eq .User.Email ""}}
        <li><a href={{.User.URL}}>Log In / Sign Up</a></li>
        {{else}}
        <li><a href="#">{{.User.Email}}</a></li>
        <li><a href={{.User.URL}}>(Log Out)</a></li>
        {{end}}
        <li><a href="/Cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> ({{.Cart.ItemCount}}) Items </a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>
{{end}}
