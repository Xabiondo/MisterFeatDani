<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Press Start 2P', cursive;
            background-color: #f4f4f9;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        h1 {
            font-size: 2em;
            margin-bottom: 20px;
        }
        p {
            font-size: 1.2em;
            margin-bottom: 30px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .menu-container {
            margin-top: 20px;
        }
        .menu-button {
            display: block;
            margin: 10px auto;
        }
    </style>
</head>
<body>

<!-- Contenedor principal -->
<div class="menu-container">
    <!-- Saludo personalizado -->
    <h1>¡Hola, ${nombreUsuario}!</h1>
    <p>Tu dinero disponible es: $${dineroDisponible}</p>

    <!-- Menú de opciones -->
    <button class="menu-button"><a href="/poner-subasta">Poner jugador a subasta</a></button>
    <button class="menu-button"><a href="/pujar-jugador">Pujar por un jugador</a></button>
    <button class="menu-button"><a href="/ver-equipo">Ver mi equipo</a></button>
    <button class="menu-button"><a href="/ver-dinero">Ver mi dinero disponible</a></button>
    <button class="menu-button"><a href="/logout">Cerrar sesión</a></button>
</div>

</body>
</html>