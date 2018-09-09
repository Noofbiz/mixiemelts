function editButtonPress(){
  var curRow = $(this.parentElement.parentElement.parentElement);
  var form = $('#' + curRow.attr('id') + 'Form')
  curRow.hide();
  form.show();
}

function cancelEditButtonPress(){
  var form = $(this.parentElement.parentElement.parentElement);
  var curRow = $('#' + form.attr('id').substring(0, (form.attr('id').length - 4)))
  form.hide();
  curRow.show();
}

function addToTags(){
  var tags = $("#AddTag").val();
  var adding = $("#TagIn").val();
  if(tags != ""){
    tags += "#NEXT#%";
  }
  tags += adding;
  $("#AddTag").val(tags);
  $("#TagDiv").append("<br>" + adding);
}

function addToRecipe(){
  var ingredients = $("#AddRecipe").val();
  var adding = $("#RecipeNameIn").val();
  var quantity = $("#RecipeQtyIn").val();
  if(ingredients != ""){
    ingredients += "#NEXT#%";
  }
  ingredients += adding + "%#ItemQty#%" + quantity;
  $("#AddRecipe").val(ingredients);
  $("#RecipeDiv").append("<br>" + adding + " Qty: " + quantity);
}

$( document ).ready(function() {
  $('button.edit-button').on('click', editButtonPress);
  $('button.cancel-edit-button').on('click', cancelEditButtonPress);
  $('button#TagButton').on('click', addToTags);
  $('button#RecipeButton').on('click', addToRecipe);
});
