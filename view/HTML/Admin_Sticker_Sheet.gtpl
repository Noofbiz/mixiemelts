{{define "admin_sticker_sheet"}}
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
          <li role="presentation"><a href="/Admin">Overview</a></li>
          <li role="presentation"><a href="/Admin/Orders">Orders</a></li>
          <li role="presentation" class="active"><a href="/Admin/StickerSheets">Sticker Sheets</a></li>
          <li role="presentation"><a href="/Admin/Inventory">Inventory</a></li>
          <li role="presentation"><a href="/Admin/Scents">Scents</a></li>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-10 col-xs-offset-1">
        <h1> This is the sticker sheet section.</h1>
        <p> Use the above links to navigate to different sections. This will eventually be updated when the sticker sheet library is ready! </p>
      </div>
    </div>
  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
