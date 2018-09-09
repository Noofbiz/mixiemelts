{{define "admin"}}
<!DOCTYPE html>
<html>

{{template "header"}}

<body>
  {{template "navbar" .}}
  <div class="container-fluid">
    <!-- Nav Pills Row -->
    <div class="row">
      <div class="col-xs-10 col-xs-offset-1">
        <ul class="nav nav-pills">
          <li role="presentation" class="active"><a href="/Admin">Overview</a></li>
          <li role="presentation"><a href="/Admin/Orders">Orders</a></li>
          <li role="presentation"><a href="/Admin/StickerSheets">Sticker Sheets</a></li>
          <li role="presentation"><a href="/Admin/Inventory">Inventory</a></li>
          <li role="presentation"><a href="/Admin/Scents">Scents</a></li>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-10 col-xs-offset-1">
        <h1> This is the admin section.</h1>
        <p> Use the above links to navigate to different sections. This will eventually be updated to provide summaries and things as necessiary. </p>
      </div>
    </div>
  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
