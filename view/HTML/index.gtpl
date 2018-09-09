{{define "indexPage"}}
<!DOCTYPE html>
<html>

{{template "header"}}

<body>
  {{template "navbar" .}}
  <div class="container-fluid">
    <!-- Call to Action Row! -->
    <div class="row">
      <!-- Carousel ================================================== -->
       <div id="myCarousel" class="carousel slide" data-ride="carousel">
         <!-- Indicators -->
         <ol class="carousel-indicators">
           <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
           <li data-target="#myCarousel" data-slide-to="1"></li>
           <li data-target="#myCarousel" data-slide-to="2"></li>
         </ol>
         <div class="carousel-inner" role="listbox">
           <div class="item active">
             <img class="first-slide" src="/static/images/index/FirstSlide.jpg" alt="First slide">
             <div class="container">
               <div class="carousel-caption">
                 <h1>100% Natural Soy Wax Tarts</h1>
                 <p>
                   Welcome to Mixie Melts! We hand pour 100% natural soy wax tarts for use in wax warmers. All of our melts are hand poured, and made when you order them to deliver the freshest tarts possible. We have a growing variety of scents to pick from, so find your favorites today!
                 </p>
                 <p>
                   <a class="btn btn-lg btn-primary" href="/Store" role="button">
                     Shop Now!
                   </a>
                 </p>
               </div>
             </div>
           </div>
           <div class="item">
             <img class="second-slide" src="/static/images/index/SecondSlide.jpg" alt="Second slide">
             <div class="container">
               <div class="carousel-caption">
                 <h1>Subscription Boxes</h1>
                 <p>
                   We offer subsciption boxes, delivered straight to your door every month! No need to re-order every time you run out!
                 </p>
                 <p><a class="btn btn-lg btn-primary" href="/Store/Boxes" role="button">Shop Subscriptions</a></p>
               </div>
             </div>
           </div>
           <div class="item">
             <img class="third-slide" src="/static/images/index/ThirdSlide.jpg" alt="Third slide">
             <div class="container">
               <div class="carousel-caption">
                 <h1>Find Us on Social Media!</h1>
                 <p>Join our Facebook group! <a href="/facebook"><i class="fa fa-facebook-official" aria-hidden="true"></i></a></p>
                 <p>Follow us on Twitter! <a href="/facebook"><i class="fa fa-twitter" aria-hidden="true"></i></a></p>
                 <p>See pictures on Instagram! <a href="/facebook"><i class="fa fa-instagram" aria-hidden="true"></i></a></p>
               </div>
             </div>
           </div>
         </div>
         <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
           <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
           <span class="sr-only">Previous</span>
         </a>
         <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
           <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
           <span class="sr-only">Next</span>
         </a>
       </div><!-- /.carousel -->
    </div>

    <hr>

    <!-- Row for panel for featured scents -->
    <div class="row">
      <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default text-center">
          <div class="panel-heading">
            <h1 class="panel-title">Scent of the Month!</h1>
          </div>
          <div class="panel-body">
            {{template "scent_of_the_month"}}
          </div>
        </div>
      </div>
    </div>

  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
