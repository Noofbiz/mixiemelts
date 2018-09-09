{{define "admin_inventory"}}
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
          <li role="presentation"><a href="/Admin/StickerSheets">Sticker Sheets</a></li>
          <li role="presentation" class="active"><a href="/Admin/Inventory">Inventory</a></li>
          <li role="presentation"><a href="/Admin/Scents">Scents</a></li>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6 col-xs-offset-1 rborder">
        <h2>Item</h2>
      </div>
      <div class="col-xs-2">
        <h2>Quantity</h2>
      </div>
    </div>
    <hr>
    {{range $index, $item := .Inventory}}
    <form id="{{printf "InvRow%vForm" $index}}" action="/Admin/Inventory/AddUpdateInventory" method="post" style="display: none;">
      <div class="row">
        <div class="col-xs-6 col-xs-offset-1 rborder">
          <input type="hidden" name="Item" value={{$item.Item}}>
          <input type="text" name="Item2" value={{$item.Item}}>
        </div>
        <div class="col-xs-2">
          <input type="hidden" name="Place" value="False">
          <input type="hidden" name="Qty" value={{$item.Qty}}>
          <input type="text" name="Qty2" value={{$item.Qty}}>
        </div>
        <div class="col-xs-2">
          <button type="submit" class="btn btn-success accept-edit-button">
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
          </button>
          <button type="button" class="btn btn-danger cancel-edit-button">
            <i class="fa fa-times-circle" aria-hidden="true"></i>
          </button>
        </div>
      </div>
    </form>
    <div id="{{printf "InvRow%v" $index}}">
      <div class="row">
        <div class="col-xs-6 col-xs-offset-1 rborder">
          <p>{{$item.Item}}</p>
        </div>
        <div class="col-xs-2">
          <p>{{$item.Qty}}</p>
        </div>
        <div class="col-xs-2">
          <button id="{{printf "EditButtonInvRow%v" $index}}" type="button" class="btn btn-success edit-button">
            <i class="fa fa-pencil" aria-hidden="true"></i>
          </button>
          <a type="button" class="btn btn-danger delete-button"
           href="{{printf "/Admin/Inventory/DeleteInventory?Item=%v" $item.Item}}">
            <i class="fa fa-trash" aria-hidden="true"></i>
          </a>
        </div>
      </div>
    </div>
    <hr>
    {{end}}
    <form action="/Admin/Inventory/AddUpdateInventory" method="post">
      <div class="row">
        <div class="col-xs-6 col-xs-offset-1 rborder">
          <input type="text" name="Item">
        </div>
        <div class="col-xs-2">
          <input type="text" name="Qty">
          <input type="hidden" name="Place" value="True">
        </div>
        <div class="col-xs-2">
          <button type="submit" class="btn btn-success accept-edit-button">
            <i class="fa fa-plus-circle" aria-hidden="true"></i>
          </button>
        </div>
      </div>
  </form>
  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
