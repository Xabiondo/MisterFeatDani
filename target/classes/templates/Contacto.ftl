<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Contacto - Fantasy</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            background: radial-gradient(circle, #0f2027, #203a43, #2c5364);
            color: #fff;
            font-family: 'Press Start 2P', cursive;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            background: #000;
            padding: 15px 25px;
        }

        .navbar .logo-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .navbar img {
            height: 40px;
        }

        .navbar h1 {
            font-size: 14px;
            color: #FFD700;
        }

        .navbar .links {
            display: flex;
            gap: 20px;
            font-size: 12px;
        }

        .navbar a {
            color: #fff;
            text-decoration: none;
        }

        .navbar a:hover {
            color: #FFD700;
        }

        .main {
            padding: 40px 20px;
            text-align: center;
        }

        .main h2 {
            font-size: 16px;
            margin-bottom: 20px;
        }

        .main p {
            font-size: 12px;
            margin-bottom: 30px;
        }

        form {
            max-width: 500px;
            margin: 0 auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
            border: 2px solid #FFD700;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 10px;
            text-align: left;
        }

        input, textarea, select {
            width: 100%;
            padding: 10px;
            font-family: 'Press Start 2P', cursive;
            font-size: 10px;
            margin-bottom: 20px;
            border: none;
            border-radius: 6px;
            background-color: #fff;
            color: #000;
        }

        textarea {
            resize: none;
            height: 100px;
        }

        button {
            background-color: #FFD700;
            color: #000;
            padding: 12px 25px;
            font-size: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #ffaa00;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="logo-container">
            <img src="balon-pixelart.png" alt="Logo">
            <h1>FantasyFútbol</h1>
        </div>
        <div class="links">
            <a href="inicio.html">Inicio</a>
            <a href="miequipo.html">Mi Equipo</a>
            <a href="mercado.html">Mercado</a>
            <a href="transacciones.html">Transacciones</a>
            <a href="ayuda.html">Ayuda</a>
            <a href="contacto.html">Contacto</a>
        </div>
        <div class="user">Dani10</div>
    </div>

    <div class="main">
        <h2>¿Tienes algo que contarnos?</h2>
        <p>Envía tus dudas, sugerencias o reclamaciones y el equipo de FantasyFútbol te responderá en breve.</p>

        <form action="#" method="post">
            <label for="tipo">Tipo de mensaje:</label>
            <select id="tipo" name="tipo">
                <option value="duda">Duda</option>
                <option value="sugerencia">Sugerencia</option>
                <option value="reclamacion">Reclamación</option>
            </select>

            <label for="nombre">Tu nombre:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="mensaje">Mensaje:</label>
            <textarea id="mensaje" name="mensaje" required ></textarea>

            <button type="submit">Enviar mensaje</button>
        </form>
    </div>
</body>
</html>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Contacto - Fantasy</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            background: radial-gradient(circle, #0f2027, #203a43, #2c5364);
            color: #fff;
            font-family: 'Press Start 2P', cursive;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            background: #000;
            padding: 15px 25px;
        }

        .navbar .logo-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .navbar img {
            height: 40px;
        }

        .navbar h1 {
            font-size: 14px;
            color: #FFD700;
        }

        .navbar .links {
            display: flex;
            gap: 20px;
            font-size: 12px;
        }

        .navbar a {
            color: #fff;
            text-decoration: none;
        }

        .navbar a:hover {
            color: #FFD700;
        }

        .main {
            padding: 40px 20px;
            text-align: center;
        }

        .main h2 {
            font-size: 16px;
            margin-bottom: 20px;
        }

        .main p {
            font-size: 12px;
            margin-bottom: 30px;
        }

        form {
            max-width: 500px;
            margin: 0 auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
            border: 2px solid #FFD700;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 10px;
            text-align: left;
        }

        input, textarea, select {
            width: 100%;
            padding: 10px;
            font-family: 'Press Start 2P', cursive;
            font-size: 10px;
            margin-bottom: 20px;
            border: none;
            border-radius: 6px;
            background-color: #fff;
            color: #000;
        }

        textarea {
            resize: none;
            height: 100px;
        }

        button {
            background-color: #FFD700;
            color: #000;
            padding: 12px 25px;
            font-size: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #ffaa00;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="logo-container">
            <img src="balon-pixelart.png" alt="Logo">
            <h1>FantasyFútbol</h1>
        </div>
        <div class="links">
            <a href="inicio.html">Inicio</a>
            <a href="miequipo.html">Mi Equipo</a>
            <a href="mercado.html">Mercado</a>
            <a href="transacciones.html">Transacciones</a>
            <a href="ayuda.html">Ayuda</a>
            <a href="contacto.html">Contacto</a>
        </div>
        <div class="user">Dani10</div>
    </div>

    <div class="main">
        <h2>¿Tienes algo que contarnos?</h2>
        <p>Envía tus dudas, sugerencias o reclamaciones y el equipo de FantasyFútbol te responderá en breve.</p>

        <form action="#" method="post">
            <label for="tipo">Tipo de mensaje:</label>
            <select id="tipo" name="tipo">
                <option value="duda">Duda</option>
                <option value="sugerencia">Sugerencia</option>
                <option value="reclamacion">Reclamación</option>
            </select>

            <label for="nombre">Tu nombre:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="mensaje">Mensaje:</label>
            <textarea id="mensaje" name="mensaje" required ></textarea>

            <button type="submit">Enviar mensaje</button>
        </form>
    </div>
</body>
</html>
