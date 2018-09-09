{{define "admin_scents"}}
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
          <li role="presentation"><a href="/Admin/Inventory">Inventory</a></li>
          <li role="presentation"  class="active"><a href="/Admin/Scents">Scents</a></li>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-2 col-xs-offset-1 rborder">
        <h2>Name</h2>
      </div>
      <div class="col-xs-2 rborder">
        <h2>Tags</h2>
      </div>
      <div class="col-xs-2 rborder">
        <h2>Description</h2>
      </div>
      <div class="col-xs-2">
        <h2>Recipe</h2>
      </div>
    </div>
    <hr>
    {{range $index, $scent := .Scents}}
    <form id="{{printf "ScentRow%vForm" $index}}" action="/Admin/Scents/AddUpdateScent" method="post" style="display: none;">
      <div class="row">
        <div class="col-xs-2 col-xs-offset-1 rborder">
          <input type="hidden" name="Name" value={{$scent.Name}}>
          <input type="text" name="Name2" value={{$scent.Name}}>
        </div>
        <div class="col-xs-2 rborder">
          <input type="hidden" name="Tags" value="
          {{range $i, $t := $scent.Tags}}
            {{if ne $i 0}}
              %NEXT#%
            {{end}}
            {{$t}}
          {{end}}
          ">
          <input type="text" name="Tags2" value="
          {{range $i, $t := $scent.Tags}}
            {{if ne $i 0}}
              %NEXT#%
            {{end}}
            {{$t}}
          {{end}}
          ">
        </div>
        <div class="col-xs-2 rborder">
          <input type="hidden" name="Description" value={{$scent.Description}}>
          <input type="text" name="Description2" value={{$scent.Description}}>
        </div>
        <div class="col-xs-2 rborder">
          <input type="hidden" name="Recipe" value="
          {{range $i, $r := $scent.Recipe}}
            {{if ne $i 0}}
              %NEXT#%
            {{end}}
            {{$r.Item}}
            %#ItemQty#%
            {{$r.Qty}}
          {{end}}
          ">
          <input type="text" name="Recipe2" value="
          {{range $i, $r := $scent.Recipe}}
            {{if ne $i 0}}
              %NEXT#%
            {{end}}
            {{$r.Item}}
            %#ItemQty#%
            {{$r.Qty}}
          {{end}}
          ">
        </div>
        <div class="col-xs-2">
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
    <div id="{{printf "ScentRow%v" $index}}">
      <div class="row">
        <div class="col-xs-2 col-xs-offset-1 rborder">
          <p>{{$scent.Name}}</p>
        </div>
        <div class="col-xs-2 rborder">
          <ul>
            {{range $t := $scent.Tags}}
            <li>
              <p>{{$t}}</p>
            </li>
            {{end}}
          </ul>
        </div>
        <div class="col-xs-2 rborder">
          <p>{{$scent.Description}}</p>
        </div>
        <div class="col-xs-2 rborder">
          <ul>
            {{range $t := $scent.Recipe}}
            <li>
              <p>{{$t.Item}} Qty: {{$t.Qty}}</p>
            </li>
            {{end}}
          </ul>
        </div>
        <div class="col-xs-2">
          <button id="{{printf "EditButtonScentRow%v" $index}}" type="button" class="btn btn-success edit-button">
            <i class="fa fa-pencil" aria-hidden="true"></i>
          </button>
          <a type="button" class="btn btn-danger delete-button"
           href="{{printf "/Admin/Scents/DeleteScent?Name=%v" $scent.Name}}">
            <i class="fa fa-trash" aria-hidden="true"></i>
          </a>
        </div>
      </div>
    </div>
    <hr>
    {{end}}
    <form action="/Admin/Scents/AddUpdateScent" method="post">
      <div class="row">
        <div class="col-xs-2 col-xs-offset-1 rborder">
          <input type="text" name="Name">
        </div>
        <div id="TagDiv" class="col-xs-2 rborder">
          <input id="AddTag" type="hidden" name="Tags" value="">
          <select id="TagIn">
            {{range $t := .Tags}}
            <option value="{{$t}}">{{$t}}</option>
            {{end}}
          </select>
          <button id="TagButton" type="button" class="btn btn-primary">
            <i class="fa fa-plus-circle" aria-hidden="true"></i>
          </button>
        </div>
        <div class="col-xs-2 rborder">
          <input type="text" name="Description">
        </div>
        <div id="RecipeDiv" class="col-xs-2 rborder">
          <input id="AddRecipe" type="hidden" name="Recipe" value="">
          <select id="RecipeNameIn">
            {{range $i := .Inventory}}
            <option value="{{$i.Item}}">{{$i.Item}}</option>
            {{end}}
          </select>
          <input id="RecipeQtyIn" type="text">
          <button id="RecipeButton" type="button" class="btn btn-primary">
            <i class="fa fa-plus-circle" aria-hidden="true"></i>
          </button>
        </div>
        <div class="col-xs-2">
          <input type="hidden" name="Place" value="True">
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
