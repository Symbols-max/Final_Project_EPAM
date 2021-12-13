<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>ChangeInfo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<style>
    /* Form Style */
    .form-horizontal{
        background: #fff;
        padding-bottom: 40px;
        border-radius: 15px;
        text-align: center;
    }
    .form-horizontal .form-group{
        padding: 0 40px;
        margin: 0 0 25px 0;
        position: relative;
    }
    .form-horizontal .form-control{
        background: #f0f0f0;
        border: none;
        border-radius: 20px;
        box-shadow: none;
        padding: 0 20px 0 45px;
        height: 40px;
        transition: all 0.3s ease 0s;
    }
    .form-horizontal .form-control:focus{
        background: #e0e0e0;
        box-shadow: none;
        outline: 0 none;
    }
    .form-horizontal .form-group i{
        position: absolute;
        top: 12px;
        left: 60px;
        font-size: 17px;
        color: #c8c8c8;
        transition : all 0.5s ease 0s;
    }
    .form-horizontal .form-control:focus + i{
        color: #00b4ef;
    }
    .form-horizontal .main-checkbox label{
        width: 20px;
        height: 20px;
        position: absolute;
        top: 0;
        left: 0;
        cursor: pointer;
    }
    .form-horizontal .main-checkbox label:after{
        content: "";
        width: 10px;
        height: 5px;
        position: absolute;
        top: 5px;
        left: 4px;
        border: 3px solid #fff;
        border-top: none;
        border-right: none;
        background: transparent;
        opacity: 0;
        -webkit-transform: rotate(-45deg);
        transform: rotate(-45deg);
    }
    .form-horizontal .main-checkbox input[type=checkbox]{
        visibility: hidden;
    }
    .form-horizontal .main-checkbox input[type=checkbox]:checked + label:after{
        opacity: 1;
    }

    .form-horizontal .btn{
        font-size: 14px;
        color: #fff;
        background: #00b4ef;
        border-radius: 30px;
        padding: 10px 25px;
        border: none;
        text-transform: capitalize;
        transition: all 0.5s ease 0s;
    }
    @media only screen and (max-width: 479px){
        .form-horizontal .form-group{
            padding: 0 25px;
        }
        .form-horizontal .form-group i{
            left: 45px;
        }
        .form-horizontal .btn{
            padding: 10px 20px;
        }
    }
</style>

<div class="container">
    <div class="row">

        <div class="col-md-offset-3 col-md-6" style="padding-top: 5%">
            <form class="form-horizontal" action="/pages/user/changeInfoEntrantServlet" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="newName" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.name")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newSurname" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.surname")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newMiddleName" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.middleName")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="newEmail" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newCity" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.city")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newRegion" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.region")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newPlaceEducation" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.placeEducation")}" required>
                </div>
                <div class="form-group">
                    <div style="display: -webkit-inline-flex">${sessionScope.get("bundle").getString("changeInfoEntrant.input.certificate")}:  <input type="file"  name="newDiplom">
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-default">${sessionScope.get("bundle").getString("changeInfoEntrant.btn.submit")}</button>
                    <p></p>
                    <p>${message}</p>
                </div>
            </form>
        </div>

    </div><!-- /.row -->
</div><!-- /.container -->
</body>
</html>