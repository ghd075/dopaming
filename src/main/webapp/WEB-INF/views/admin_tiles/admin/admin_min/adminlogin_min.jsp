<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="./resources/css/style.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<title> Bootstrap Form Style </title>
</head>

<body>
 <div class="demo form-bg">
        <div class="container-fluid">
            <div class="row text-center">
                <div class="col-lg-12">
                    <h1 class="heading-title"> DOPAMING STYLE</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form action="login" method="post">
                        <h1 class="heading">ADMINLOGIN</h1>
                        <div class="form-group">
                            <label class="control-label">ID</label>
                            <input type="text" name="member_id" value="${members.member_id}" class="form-control" placeholder="Please enter your ID"/>

                            <label class=" control-label">Password</label>
                            <input autocomplete="off" name="member_password" value="${members.member_password }" type="password" class="form-control" placeholder="Please enter a password"/>

                            <button class="btn btn-default">LOGIN 
                            <i class="fa fa-arrow-circle-right fa-2x"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
