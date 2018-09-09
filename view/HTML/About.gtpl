{{define "about"}}
<!DOCTYPE html>
<html>

{{template "header"}}

<body>
  {{template "navbar" .}}
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-info">
          <div class="panel-body">
            <div class="row">
              <div class="col-md-4">
                <img src="/static/images/about/AboutMM.jpg" class="img-responsive img-circle" alt="MixieMelt Logo">
              </div>
              <div class="col-md-8">
                <h1 class="text-center">Mixie Melts</h1>
                <p>Proin aliquet lectus vitae posuere suscipit. Sed pulvinar nibh at lacus aliquam pellentesque. Fusce ut accumsan lorem. Aenean sollicitudin eros justo, non pulvinar justo vehicula malesuada. Nulla in convallis dolor. Sed quis orci ligula. Donec consequat enim in metus mollis porta. Quisque dapibus tellus ut pretium placerat. Aenean quis odio sed nunc finibus egestas. Maecenas ac vehicula leo, nec efficitur augue.</p>
                <p>Vestibulum id volutpat nisi, non aliquet ipsum. Vestibulum risus felis, scelerisque a augue at, rutrum consequat massa. Phasellus ut odio massa. Mauris vehicula ipsum in placerat blandit. Integer quis nisi euismod, commodo nisi in, ultricies nisl. Praesent tempor gravida nunc, ut fringilla neque faucibus vitae. Donec non euismod ipsum. Proin pretium scelerisque scelerisque. Vestibulum ornare vel dolor at tristique. Phasellus malesuada enim sit amet nibh blandit tempus. Phasellus porta sem ut lacinia porta.</p>
                <p>Vivamus finibus fermentum molestie. Fusce tincidunt facilisis fermentum. Nunc placerat dolor vel erat porta tristique. Morbi metus leo, consectetur ut egestas id, consectetur id quam. Ut vulputate ipsum at vestibulum pretium. Praesent ut orci pellentesque, euismod nunc non, rhoncus nunc. Suspendisse in lorem ullamcorper, egestas sapien quis, auctor mi. Etiam vel efficitur ante, sed efficitur odio. Morbi non molestie arcu. Curabitur consequat, urna a lacinia eleifend, quam dui faucibus lorem, sed pulvinar purus diam a magna. Quisque gravida vel tortor at lobortis.</p>
                <p>Maecenas in ex cursus, iaculis metus a, iaculis dui. Maecenas vitae sapien sit amet elit imperdiet volutpat eget a est. Nam a commodo arcu. In sit amet rhoncus elit, sit amet finibus metus. Quisque quis quam lacus. Sed id turpis euismod, tincidunt eros ac, porttitor nisl. Nullam blandit non justo cursus ultricies. Sed mollis metus ligula, vel ullamcorper velit dictum non. Nulla accumsan, tellus a tristique interdum, sapien tortor laoreet neque, et dignissim quam nunc quis lectus. In eu magna nisl.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-info">
          <div class="panel-body">
            <div class="row">
              <div class="col-md-8">
                <h1 class="text-center">Our Team</h1>
                <p>Proin aliquet lectus vitae posuere suscipit. Sed pulvinar nibh at lacus aliquam pellentesque. Fusce ut accumsan lorem. Aenean sollicitudin eros justo, non pulvinar justo vehicula malesuada. Nulla in convallis dolor. Sed quis orci ligula. Donec consequat enim in metus mollis porta. Quisque dapibus tellus ut pretium placerat. Aenean quis odio sed nunc finibus egestas. Maecenas ac vehicula leo, nec efficitur augue.</p>
                <p>Vestibulum id volutpat nisi, non aliquet ipsum. Vestibulum risus felis, scelerisque a augue at, rutrum consequat massa. Phasellus ut odio massa. Mauris vehicula ipsum in placerat blandit. Integer quis nisi euismod, commodo nisi in, ultricies nisl. Praesent tempor gravida nunc, ut fringilla neque faucibus vitae. Donec non euismod ipsum. Proin pretium scelerisque scelerisque. Vestibulum ornare vel dolor at tristique. Phasellus malesuada enim sit amet nibh blandit tempus. Phasellus porta sem ut lacinia porta.</p>
                <p>Vivamus finibus fermentum molestie. Fusce tincidunt facilisis fermentum. Nunc placerat dolor vel erat porta tristique. Morbi metus leo, consectetur ut egestas id, consectetur id quam. Ut vulputate ipsum at vestibulum pretium. Praesent ut orci pellentesque, euismod nunc non, rhoncus nunc. Suspendisse in lorem ullamcorper, egestas sapien quis, auctor mi. Etiam vel efficitur ante, sed efficitur odio. Morbi non molestie arcu. Curabitur consequat, urna a lacinia eleifend, quam dui faucibus lorem, sed pulvinar purus diam a magna. Quisque gravida vel tortor at lobortis.</p>
                <p>Maecenas in ex cursus, iaculis metus a, iaculis dui. Maecenas vitae sapien sit amet elit imperdiet volutpat eget a est. Nam a commodo arcu. In sit amet rhoncus elit, sit amet finibus metus. Quisque quis quam lacus. Sed id turpis euismod, tincidunt eros ac, porttitor nisl. Nullam blandit non justo cursus ultricies. Sed mollis metus ligula, vel ullamcorper velit dictum non. Nulla accumsan, tellus a tristique interdum, sapien tortor laoreet neque, et dignissim quam nunc quis lectus. In eu magna nisl.</p>
              </div>
              <div class="col-md-4">
                <img src="/static/images/about/TeamPic.jpg" class="img-responsive img-circle" alt="Mixie Melts Team">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div> <!-- /container -->
  {{template "footer"}}
</body>

</html>
{{end}}
