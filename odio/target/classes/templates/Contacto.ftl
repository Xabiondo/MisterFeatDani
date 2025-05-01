<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contacto - Fantasy Fútbol España</title>
</head>
<body>
<h1>Contacto</h1>

<p>Si tienes alguna pregunta, sugerencia o problema relacionado con Fantasy Fútbol España, no dudes en contactarnos.</p>

<!-- Formulario de contacto -->
<h2>Formulario de Contacto</h2>
<form action="/enviar-contacto" method="post">
    <label for="nombre">Nombre:</label><br>
    <input type="text" id="nombre" name="nombre" placeholder="Tu nombre" required><br><br>

    <label for="email">Correo Electrónico:</label><br>
    <input type="email" id="email" name="email" placeholder="ejemplo@correo.com" required><br><br>

    <label for="mensaje">Mensaje:</label><br>
    <textarea id="mensaje" name="mensaje" rows="5" cols="40" placeholder="Escribe tu mensaje aquí..." required></textarea><br><br>

    <button type="submit">Enviar Mensaje</button>
</form>

<h3>Otras Formas de Contacto</h3>
<p>Puedes contactarnos también a través de los siguientes medios:</p>
<ul>
    <li>Correo electrónico: <a href="mailto:soporte@fantasyfutbolespana.com">soporte@fantasyfutbolespana.com</a></li>
    <li>Teléfono: +34 123 456 789</li>
    <li>Redes Sociales:
        <ul>
            <li>Twitter: <a href="https://twitter.com/fantasyfutbol">@fantasyfutbol</a></li>
            <li>Instagram: <a href="https://instagram.com/fantasyfutbol">@fantasyfutbol</a></li>
        </ul>
    </li>
</ul>

<footer>
    <p>&copy; 2023 Fantasy Fútbol España. Todos los derechos reservados.</p>
</footer>
</body>
</html>