{{define "admin_orders"}}
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
          <li role="presentation" class="active"><a href="/Admin/Orders">Orders</a></li>
          <li role="presentation"><a href="/Admin/StickerSheets">Sticker Sheets</a></li>
          <li role="presentation"><a href="/Admin/Inventory">Inventory</a></li>
          <li role="presentation"><a href="/Admin/Scents">Scents</a></li>
        </ul>
      </div>
    </div>
    {{range $index, $order := .Orders}}
    <div class="panel">
      <div class="panel-heading">
        {{$order.OrderNum}}
      </div>
      <div class="panel-body">
        <form id="{{printf "OrderRow%vForm" $index}}" action="/Admin/Orders/PlaceUpdateOrder" method="post" style="display: none;">
          <div class="row">
            <div class="col-xs-5 col-xs-offset-1 panel panel-info">
              <div class="panel-heading">
                Items
              </div>
              <div class="panel-body">
                Change by adding cookie and then updating.
              </div>
            </div>
            <div class="col-xs-3 panel panel-info">
              <div class="panel-heading">Email</div>
              <div class="panel-body">
                <input type="hidden" name="Email" value={{$order.Email}}>
                <input type="text" name="Email2" value={{$order.Email}}>
              </div>
            </div>
            <div class="col-xs-1 panel panel-info">
              <div class="panel-body">
                <input type="hidden" name="Paid" value={{if $order.Paid}}True{{else}}False{{end}}>
                Paid: <input type="checkbox" name="Paid2" {{if $order.Paid}}checked{{end}} value="True">
              </div>
            </div>
            <div class="col-xs-1 panel panel-info">
              <div class="panel-body">
                <input type="hidden" name="Made" value={{if $order.Made}}True{{else}}False{{end}}>
                Made: <input type="checkbox" name="Made2" {{if $order.Made}}checked{{end}} value="True">
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-4 col-xs-offset-1 panel panel-info">
              <div class="panel-heading">Name</div>
              <div class="panel-body">
                <input type="hidden" name="Name" value={{$order.Name}}>
                <input type="text" name="Name2" value={{$order.Name}}>
              </div>
            </div>
            <div class="col-xs-5 panel panel-info">
              <div class="panel-heading">Address</div>
              <div class="panel-body">
                <input type="hidden" name="Address" value={{$order.Address}}>
                <input type="text" name="Address2" value={{$order.Address}}>
              </div>
            </div>
            <div class="col-xs-1 panel panel-info">
              <div class="panel-body">
                <input type="hidden" name="Sent" value={{if $order.Sent}}True{{else}}False{{end}}>
                Sent: <input type="checkbox" name="Sent2" {{if $order.Sent}}checked{{end}} value="True">
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-8 col-xs-offset-1 panel panel-info">
              <div class="panel-heading">Tracking</div>
              <div class="panel-body">
                <input type="hidden" name="Tracking" value={{$order.Tracking}}>
                <input type="text" name="Tracking2" value={{$order.Tracking}}>
              </div>
            </div>
            <div class="col-xs-2 panel panel-info">
              <input type="hidden" name="OrderNum" value={{$order.OrderNum}}>
              <input type="hidden" name="Place" value="False">
              <button type="submit" class="btn btn-success accept-edit-button">
                <i class="fa fa-check-square-o" aria-hidden="true"></i>
              </button>
              <button type="button" class="btn btn-danger cancel-edit-button">
                <i class="fa fa-times-circle" aria-hidden="true"></i>
              </button>
            </div>
          </div>
      </form>
        <div id="{{printf "OrderRow%v" $index}}">
          <div class="row">
            <div class="col-xs-5 col-xs-offset-1 panel panel-info">
              <div class="panel-heading">Items</div>
              <div class="panel-body">
                {{range $idx, $itm := $order.Item}}
                  {{$itm.ItemName}} Qty: {{$itm.Qty}}
                {{end}}
              </div>
            </div>
            <div class="col-xs-3 panel panel-info">
              <div class="panel-heading">Email</div>
              {{$order.Email}}
            </div>
            <div class="col-xs-1 panel panel-info">
              Paid: <input type="checkbox" disabled {{if $order.Paid}}checked{{end}}>
            </div>
            <div class="col-xs-1 panel panel-info">
              Made: <input type="checkbox" disabled {{if $order.Made}}checked{{end}}>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-4 col-xs-offset-1 panel panel-info">
              <div class="panel-heading">Name</div>
              {{$order.Name}}
            </div>
            <div class="col-xs-5 panel panel-info">
              <div class="panel-heading">Address</div>
              {{$order.Address}}
            </div>
            <div class="col-xs-1 panel panel-info">
              Sent: <input type="checkbox" disabled {{if $order.Sent}}checked{{end}}>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-8 col-xs-offset-1 panel panel-info">
              <div class="panel-heading">Tracking</div>
              {{$order.Tracking}}
            </div>
            <div class="col-xs-2 panel panel-info editdel">
              <button id="{{printf "EditButtonOrderRow%v" $index}}" type="button" class="btn btn-success edit-button">
                <i class="fa fa-pencil" aria-hidden="true"></i>
              </button>
              <a type="button" class="btn btn-danger delete-button"
               href="{{printf "/Admin/Orders/DeleteOrder?OrderNum=%v" $order.OrderNum}}">
                <i class="fa fa-trash" aria-hidden="true"></i>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    {{ end }}
  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
