<!DOCTYPE html>
<head>
    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html{
            height: 100vh;
            background: linear-gradient(
                135deg,
                rgb(243, 148, 243),
                rgb(81, 116, 211)
            );
            font-family: Arial, Helvetica, sans-serif;
        }

        .form-container {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }


        .form-box {
            height: auto;
            width: 30%;
            background: rgba(255, 255, 255, 0.4);
            padding: 10px;
            border: 1px solid whitesmoke;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .form-box h2  {
            margin: 15px 0 25px 0;
            color: white;
        }

        .form-box form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .form-box form input, .form-box form button {
            height: 35px;
            border-radius: 5px;
            padding-left: 5px;
            border: unset;
        }

        .form-box form input:focus{
            outline: 1px solid green;
        }
        
        .form-box form button {
            cursor: pointer;
            background-color: green;
            color: white;
            font-size: 1.1em;
        }

        .err {
            color: red;
            font-size: 0.8em;
        }


    </style>
</head>
<html>
    <body>      
        <div class="form-container"> 
            <div class="form-box">
                <h2>Se connecter</h2>    
                <form method='POST' action='controleur1.php'>
                    <input name='login' placeholder="Login" required>
                    <input type='password' name='password' placeholder="Password">
                    <?php
                        if (isset($_GET['err'])) {
                            echo "<span class=\"err\">" . $_GET['err'] . "</span>";
                        }
                    ?>
                    <button>Se connecter</button>
                </form>
            </div>

        </div>
    </body>
</html>


