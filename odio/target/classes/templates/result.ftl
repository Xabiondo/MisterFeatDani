<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado del Formulario</title>
    <style>
        /* Estilo general */
        body {
            font-family: 'Creepster', cursive; /* Fuente escalofriante */
            background-color: #000; /* Fondo negro */
            background-image: url('https://i.imgur.com/8y9vZ6z.jpg'); /* Imagen de fondo con temática de Halloween */
            background-size: cover;
            background-position: center;
            color: #fff; /* Texto blanco */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            overflow: hidden;
        }

        /* Contenedor de resultados */
        .result-container {
            background: rgba(0, 0, 0, 0.8); /* Fondo semi-transparente */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.5); /* Sombra roja siniestra */
            width: 350px;
            position: relative;
            overflow: hidden;
        }

        /* Título */
        h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 2.5rem;
            text-shadow: 2px 2px 5px red; /* Sombra roja en el título */
            animation: flicker 1.5s infinite alternate; /* Parpadeo espeluznante */
        }

        /* Párrafos */
        p {
            margin-bottom: 10px;
            font-size: 1rem;
            text-shadow: 1px 1px 3px red; /* Sombra roja en los párrafos */
        }

        /* Enlace para volver al formulario */
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #ff0000; /* Rojo sangre */
            text-decoration: none;
            font-size: 1.2rem;
            transition: transform 0.3s ease, color 0.3s ease;
        }

        a:hover {
            color: #8b0000; /* Rojo más oscuro al pasar el mouse */
            transform: scale(1.1); /* Efecto de agrandamiento */
            text-shadow: 0 0 10px red; /* Resplandor rojo */
        }

        /* Animación de parpadeo para el título */
        @keyframes flicker {
            0%, 18%, 22%, 25%, 53%, 57%, 100% {
                text-shadow: 2px 2px 5px red;
            }
            20%, 24%, 55% {
                text-shadow: none;
            }
        }

        /* Añadir una animación de neblina en el fondo */
        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 200%;
            height: 200%;
            background: url('https://i.imgur.com/xyz1234.png') repeat; /* Imagen de neblina */
            animation: fog-move 15s linear infinite;
            opacity: 0.5;
            z-index: -1;
        }

        @keyframes fog-move {
            0% {
                transform: translateX(0) translateY(0);
            }
            100% {
                transform: translateX(-50%) translateY(-50%);
            }
        }
    </style>
</head>
<body>
<div class="result-container">
    <h1>Resultado del Formulario</h1>
    <p><strong>ID:</strong> ${id}</p>
    <p><strong>Nombre:</strong> ${nombre}</p>
    <p><strong>Sueldo:</strong> $${sueldo}</p>
    <p><strong>Tipo de Contrato:</strong> ${contrato}</p>
    <p><strong>Departamento:</strong> ${departamento}</p>
    <a href="/">Volver al formulario</a>
</div>
</body>
</html>