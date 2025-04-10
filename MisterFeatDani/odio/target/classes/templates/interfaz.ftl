<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido - Fantasy Liga</title>
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: white;
        }

        /* Contenedor principal */
        .menu-container {
            background: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 100%;
            max-width: 400px;
        }

        /* Título y saludo */
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
            margin-bottom: 30px;
        }

        /* Botones del menú */
        .menu-button {
            display: block;
            background: #007BFF;
            color: white;
            padding: 15px;
            margin: 10px auto;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.3s ease;
            width: 80%;
        }

        .menu-button:hover {
            background: #0056b3;
        }

        /* Enlaces */
        .menu-button a {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="menu-container">
    <!-- Saludo personalizado -->
    <h1>¡Hola,!</h1>
    <p>Bienvenido a tu panel de Fantasy Liga. Aquí puedes gestionar tu equipo y participar en subastas.</p>

    <!-- Menú de opciones -->
    <button class="menu-button"><a href="/poner-subasta">Poner jugador a subasta</a></button>
    <button class="menu-button"><a href="/pujar-jugador">Pujar por un jugador</a></button>
    <button class="menu-button"><a href="/ver-equipo">Ver mi equipo</a></button>
    <button class="menu-button"><a href="/ver-dinero">Ver mi dinero disponible</a></button>
    <button class="menu-button"><a href="/logout">Cerrar sesión</a></button>
</div>
</body>
</html>