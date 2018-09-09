{{define "store"}}
<!DOCTYPE html>
<html>

{{template "header"}}

<body>
  {{template "navbar" .}}
  <div class="container-fluid">
    {{range $index, $tag := .Tags}}
      {{if mod $index 2}}
        <div class="row">
          <div class="col-md-offset-1 col-md-5">
      {{else}}
          <div class="col-md-5">
      {{end}}
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2 class="panel-title text-center">{{$tag}}</h2>
              </div>
              <div class="panel-body">
                <a href="/Store?Tag={{$tag}}">
                  <img class="img-responsive" src="/static/images/store/tags/{{$tag}}.jpg" alt="Tag {{$tag}}">
                </a>
              </div>
            </div>
          </div>
      {{if mod $index 2}}
      {{else}}
        </div>
      {{end}}
    {{end}}
    {{range $index, $scent := .Scents}}
    {{if mod $index 3}}
      <div class="row">
        <div class="col-md-offset-1 col-md-3">
    {{else}}
        <div class="col-md-3">
    {{end}}
          <div class="panel panel-default">
            <div class="panel-heading">
              <h2 class="panel-title text-center">{{$scent.Name}}</h2>
            </div>
            <div class="panel-body">
              <a href="/Store?Scent={{$scent.Name}}">
                <img class="img-responsive" src="/static/images/store/scents/{{$scent.Name}}.jpg" alt="Picture for {{$scent.Name}}">
              </a>
            </div>
          </div>
        </div>
    {{$incIndex := inc $index}}
    {{if mod $incIndex 3}}
      </div>
    {{end}}
    {{end}}
    {{if ne .Scent.Name ""}}
    <div class="row">
      <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h2 class="panel-title text-center">{{.Scent.Name}}</h2>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-md-4">
                <img class="img-responsive" src="/static/images/store/scents/{{.Scent.Name}}.jpg" alt="Picture for {{.Scent.Name}}">
              </div>
              <div class="col-md-6">
                <p>{{.Scent.Description}}</p>
              </div>
              <div class="col-md-2">
                <form action="/AddToCart" method="post">
                  <!-- Name Qty Weight -->
                  <input name="Name" type="hidden" value="Wax Tart - {{.Scent.Name}}">
                  <input name="Weight" type="hidden" value="1">
                  <input name="Qty" type="number" value="1" min="1" max="12">
                  <button type="submit" class="btn btn-success accept-edit-button">
                    <i class="fa fa-cart-plus" aria-hidden="true"></i>
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    {{end}}
  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
