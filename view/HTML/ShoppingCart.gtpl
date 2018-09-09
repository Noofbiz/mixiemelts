{{define "ShoppingCart"}}
<!DOCTYPE html>
<html>

{{template "header"}}

<body>
  {{template "navbar" .}}
  <div class="container-fluid">
    <div class="row">
      <div class="col-xs-10 col-xs-offset-1 well">
        <table class="table table-striped">
          <tr>
            <th>Item</th>
            <th>Qty</th>
            <th>Price</th>
            <th>Remove From Cart</th>
          </tr>
          {{$price_mult := .PriceMult}}
          {{range $item := .Cart.Items}}
          <tr>
            <td>{{$item.ItemName}}</td>
            <td>
              <form action="/UpdateQtyCart" method="post">
                <input type="hidden" name="id" value={{$item.ID}}>
                <input name="Qty" type="number" value={{$item.Qty}} min="1" max="12">
                <button type="submit" class="btn btn-success">
                  <i class="fa fa-pencil-square" aria-hidden="true"></i>
                </button>
              </form>
            </td>
            <td>{{multiply $price_mult $item.Qty}}</td>
            <td>
              <form action="/RemoveFromCart" method="post">
                <input type="hidden" name="id" value={{$item.ID}}>
                <button type="submit" class="btn btn-warning">
                  <i class="fa fa-times-circle" aria-hidden="true"></i>
                </button>
              </form>
            </td>
          </tr>
          {{end}}
          <tr>
            <td></td>
            <td></td>
            <td>Sub Total:</td>
            <td>${{.SubTotal}}</td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td>Shipping:</td>
            <td>${{.Shipping}}</td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td><b>Total:</b></td>
            <td>${{.Total}}</td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
              <div id="paypal-button" style="width: 50%"></div>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div> <!-- /container -->
  <script src="https://www.paypalobjects.com/api/checkout.js"></script>
  <script>
    paypal.Button.render({

        env: 'sandbox', // Optional: specify 'production' environment
        style: { size: 'responsive' },

        payment: function() {
            // Set up the payment here, when the buyer clicks on the button
            var CREATE_PAYMENT_URL = '/Paypal/CreatePayment';

            return paypal.request.post(CREATE_PAYMENT_URL)
                .then(function(data) { resolve(data.paymentID); })
                .catch(function(err) { reject(err); });
        },


          onAuthorize: function(data) {

              // Note: you can display a confirmation page before executing

              var EXECUTE_PAYMENT_URL = '/paypal/execute-payment';

              return paypal.request.post(EXECUTE_PAYMENT_URL,
                      { paymentID: data.paymentID, payerID: data.payerID })

                  .then(function(data) { /* Go to a success page */ })
                  .catch(function(err) { /* Go to an error page  */ });
          }

      }, '#paypal-button');
  </script>
</body>

</html>
{{end}}
