<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        /* CSS */
        body {
            font-family: 'Press Start 2P', cursive;
            background: linear-gradient(135deg, #00b140, #0c9c63); /* Fondo verde fútbol */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            position: relative;
            overflow: hidden;
        }

        .login-container {
            background: rgba(0, 0, 0, 0.7);
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            width: 320px;
            text-align: center;
            color: white;
            position: relative;
            z-index: 2;
            animation: fadeIn 0.6s;
        }

        h2 {
            margin-bottom: 10px;
            color: #fff;
            font-size: 24px;
            text-transform: uppercase;
        }

        p {
            color: #ddd;
            font-size: 14px;
            margin-bottom: 20px;
        }

        .input-group {
            margin-bottom: 15px;
            text-align: left;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #fff;
        }

        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 14px;
            margin-bottom: 15px;
        }

        .options a {
            color: #FF6347; /* Tomato */
            text-decoration: none;
        }

        .options a:hover {
            text-decoration: underline;
        }

        button {
            background: #FF6347;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s;
        }

        button:hover {
            background: #FF4500;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* Lluvia de balones */
        .ball {
            position: absolute;
            top: -50px;
            left: calc(100% * var(--random-x));
            animation: fall 2s infinite linear;
            z-index: 1;
        }

        @keyframes fall {
            0% {
                top: -50px;
            }
            100% {
                top: 100vh;
            }
        }

        /* Agregar varios balones al fondo */
        .ball-1 { --random-x: 0.1; animation-duration: 2.5s; }
        .ball-2 { --random-x: 0.4; animation-duration: 3.2s; }
        .ball-3 { --random-x: 0.6; animation-duration: 2.8s; }
        .ball-4 { --random-x: 0.3; animation-duration: 3.0s; }
        .ball-5 { --random-x: 0.7; animation-duration: 2.7s; }
        .ball-6 { --random-x: 0.9; animation-duration: 2.9s; }
        .ball-7 { --random-x: 0.2; animation-duration: 2.6s; }
        .ball-8 { --random-x: 0.8; animation-duration: 3.0s; }
        .ball-9 { --random-x: 0.5; animation-duration: 2.9s; }
        .ball-10 { --random-x: 0.3; animation-duration: 2.8s; }

    </style>
</head>
<body>
<!-- Lluvia de balones de fútbol  -->
<div class="ball ball-1"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-2"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-3"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-4"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-5"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-6"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-7"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-8"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-9"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>
<div class="ball ball-10"><img src="https://png.pngtree.com/png-clipart/20220123/original/pngtree-football-pixel-decorative-pattern-png-image_7162607.png" width="70" height="70"></div>


<!-- Contenedor de login -->
<div class="login-container">
    <h2>Bienvenido</h2>
    <p>Por favor, inicia sesión para continuar</p>
    <form action="/interfaz" method="POST">
        <div class="input-group">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" required>
        </div>

        <div class="input-group">
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="options">
            <label><input type="checkbox" name="remember"> Recuérdame</label>
            <a href="#">¿Olvidaste tu contraseña?</a>
        </div>

        <button type="submit">Ingresar</button>
    </form>

    <p>¿No tienes cuenta todavía? <a href="registro">Registrarse</a></p>
</div>
</body>
</html>
