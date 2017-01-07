-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-01-2017 a las 03:11:57
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `scamlo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion_solicitud`
--

CREATE TABLE IF NOT EXISTS `asignacion_solicitud` (
  `asignacion_id` int(11) NOT NULL,
  `solicitud_id` int(11) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `fecha_hora_inicio` datetime NOT NULL,
  `fecha_hora_fin` datetime DEFAULT NULL,
  `equipo_reparado` varchar(80) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero_inventario` int(11) DEFAULT NULL,
  `observaciones` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asignacion_solicitud`
--

INSERT INTO `asignacion_solicitud` (`asignacion_id`, `solicitud_id`, `estado_id`, `usuario_id`, `fecha_hora_inicio`, `fecha_hora_fin`, `equipo_reparado`, `numero_inventario`, `observaciones`) VALUES
(5, 20, 3, 55, '2017-01-06 19:57:42', NULL, 'null', 0, 'null'),
(6, 21, 3, 57, '2017-01-06 20:01:56', NULL, 'null', 0, 'null'),
(7, 23, 3, 55, '2017-01-06 20:02:11', NULL, 'null', 0, 'null'),
(8, 22, 3, 57, '2017-01-06 20:04:09', NULL, 'null', 0, 'null');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dependencia`
--

CREATE TABLE IF NOT EXISTS `dependencia` (
  `id` int(11) NOT NULL,
  `nombre_dependencia` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `dependencia`
--

INSERT INTO `dependencia` (`id`, `nombre_dependencia`) VALUES
(3, 'Biblioteca'),
(4, 'Extension'),
(5, 'Coordinación Tencnologia en Sistemas'),
(6, 'Coordinacion de TICs'),
(7, 'Mantenimiento y Logistica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edificio`
--

CREATE TABLE IF NOT EXISTS `edificio` (
  `edificio_id` int(11) NOT NULL,
  `nombre_edificio` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `ubicacion` varchar(80) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'Universidad del valle - sede yumbo'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `edificio`
--

INSERT INTO `edificio` (`edificio_id`, `nombre_edificio`, `ubicacion`) VALUES
(5, 'Martin Elias', 'Lado derecho de la entrada principal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio`
--

CREATE TABLE IF NOT EXISTS `espacio` (
  `espacio_id` int(11) NOT NULL,
  `nombre` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `capacidad` int(11) NOT NULL,
  `ubicacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'Universidad del valle - sede yumbo',
  `edificio_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `espacio`
--

INSERT INTO `espacio` (`espacio_id`, `nombre`, `codigo`, `capacidad`, `ubicacion`, `edificio_id`) VALUES
(3, 'Auditorio', '100', 100, '', 5),
(5, 'Aula de clases', '105', 40, '', 5),
(7, 'Biblioteca', '300', 20, '', 5),
(9, 'Oficina Univalle', '209', 20, '', 5),
(10, 'Sala de sistemas', '1', 60, '', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE IF NOT EXISTS `estado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(48) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id`, `nombre`) VALUES
(1, 'Solucionado'),
(2, 'Pendiente'),
(3, 'No Realizado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` smallint(6) NOT NULL,
  `role_name` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `role_value` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `role_name`, `role_value`) VALUES
(1, 'Administrador', 40),
(2, 'Administrativo', 30),
(3, 'MantenimientoLogistica', 20),
(4, 'Usuario', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE IF NOT EXISTS `servicio` (
  `id` int(11) NOT NULL,
  `nombre_servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `nombre_servicio`) VALUES
(3, 'Logistica'),
(4, 'Mantenimiento');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE IF NOT EXISTS `solicitud` (
  `id` int(11) NOT NULL,
  `dependencia_id` int(11) NOT NULL,
  `servicio_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `espacio_id` int(11) NOT NULL,
  `descripcion_otros` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero_piso` int(11) NOT NULL,
  `fecha` varchar(48) COLLATE utf8_spanish_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `estado_id` int(11) NOT NULL DEFAULT '2',
  `descripcion_estado` varchar(255) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'Tu solicitud ha sido enviada y actualmente se encuentra a la espera de ser procesada'
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`id`, `dependencia_id`, `servicio_id`, `description`, `espacio_id`, `descripcion_otros`, `numero_piso`, `fecha`, `user_id`, `estado_id`, `descripcion_estado`) VALUES
(20, 5, 4, 'El ventilador esta muy sucio', 9, '', 2, '2017-01-07', 54, 2, 'Tu solicitud ha sido enviada y actualmente se encuentra a la espera de ser procesada'),
(21, 3, 3, 'Se necesitan 2 escritorios', 7, '', 3, '2017-01-07', 65, 2, 'Tu solicitud ha sido enviada y actualmente se encuentra a la espera de ser procesada'),
(22, 6, 4, 'Se deben lavar todas las sillas', 10, '', 2, '2017-01-07', 64, 2, 'Tu solicitud ha sido enviada y actualmente se encuentra a la espera de ser procesada'),
(23, 7, 4, 'No prende la lampara fluorescente', 5, '', 1, '2017-01-07', 1, 2, 'Tu solicitud ha sido enviada y actualmente se encuentra a la espera de ser procesada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `id` smallint(6) NOT NULL,
  `status_name` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `status_value` smallint(6) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `status`
--

INSERT INTO `status` (`id`, `status_name`, `status_value`) VALUES
(1, 'Activo', 10),
(2, 'Inactivo', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_espacio`
--

CREATE TABLE IF NOT EXISTS `tipo_espacio` (
  `tipo_espacio_id` int(11) NOT NULL,
  `nombre_tipo` varchar(80) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_espacio`
--

INSERT INTO `tipo_espacio` (`tipo_espacio_id`, `nombre_tipo`) VALUES
(1, 'Auditorio'),
(2, 'Aula de clases'),
(3, 'Biblioteca'),
(4, 'Oficina Univalle'),
(5, 'Sala de sistemas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `nombre_completo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `cedula` varchar(35) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(32) COLLATE utf8_spanish_ci DEFAULT NULL,
  `auth_key` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `role_id` smallint(6) NOT NULL DEFAULT '10',
  `status_id` smallint(6) NOT NULL DEFAULT '10',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `nombre_completo`, `cedula`, `telefono`, `auth_key`, `password_hash`, `password_reset_token`, `email`, `role_id`, `status_id`, `created_at`, `updated_at`) VALUES
(1, 'Carlos Arango', '123456', '4447272', 'ZxDnEj6KZD2-1ZFnnc11_b-VKwGQGafM', '$2y$13$gZqgY3crxvoQJKQYYQ7xq./YdPhnIS3cl159QbeklP4ittHn3WJ1G', NULL, 'carlos.f.arango@prueba.com', 40, 10, '2016-05-20 22:10:29', '2016-05-20 22:10:29'),
(54, 'Jose Lopez Marin', '12121212', '8888888888', 'U8_vNtoprqOVFblmnVIEaMTsqqZxFetI', '$2y$13$ocsl2OzR9ZjgO43.28ZEsORSX76NxBPJlaSYxURrEcgPO.gfncT4G', NULL, 'jose@hotmail.com', 30, 10, '2016-10-10 20:51:29', '2016-12-17 21:37:38'),
(55, 'Yovaldino Ipia Guejia', '145123456', '1234567', 'Vj2c068yap_5TDpPwGJSivplm0G92nQF', '$2y$13$obMgmdYORZtVjHoMec1mY.SIW17hYTT2npBOkkFhMhgiozanPWVKq', NULL, 'jovaldino@hotmail.com', 20, 10, '2016-11-24 16:11:34', '2017-01-06 20:34:10'),
(57, 'Pablo Jose Gallego', '1441234567', '3151234567', 'Vj2c068yap_5TDpPwGJSivplm0G92nQF', '$2y$13$gZqgY3crxvoQJKQYYQ7xq./YdPhnIS3cl159QbeklP4ittHn3WJ1G', NULL, 'pablo.gallego@correounivalle.edu.co', 20, 10, '2016-12-19 00:47:21', '2017-01-06 19:41:31'),
(64, 'Jesus Alberto Gonzalez', '1143830', '3141234567', 'LqT15dQLyaJjd9sJnhPHuSyiFKLGoOsw', '$2y$13$Vsvs4vXXDd7QyMmUNZGEUOxloKPfZHDJ4eUnKwzclG0ZasPwdTyPu', NULL, 'jesus.a.gonzalez@correounivalle.edu.co', 30, 10, '2017-01-06 19:39:43', '2017-01-06 19:39:43'),
(65, 'Aleyda Cantero', '1146123456', '3201234567', 'f0mj4CSbYfR0zBfhFWbfLkYWkhKH4EWF', '$2y$13$dUvbW69Vy9Rn4y4jabFdWOuAM4BBjpOoP/X9pMkm3AY8qzYK7fDau', NULL, 'aleyda.cantero@correounivalle.edu.co', 30, 10, '2017-01-06 19:44:31', '2017-01-06 19:44:31');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignacion_solicitud`
--
ALTER TABLE `asignacion_solicitud`
  ADD PRIMARY KEY (`asignacion_id`), ADD KEY `fk_asignacion_solicitud_solicitud` (`solicitud_id`), ADD KEY `fk_asignacion_solicitud_usuario` (`usuario_id`), ADD KEY `fk_asignacion_solicitud_estado` (`estado_id`);

--
-- Indices de la tabla `dependencia`
--
ALTER TABLE `dependencia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `edificio`
--
ALTER TABLE `edificio`
  ADD PRIMARY KEY (`edificio_id`);

--
-- Indices de la tabla `espacio`
--
ALTER TABLE `espacio`
  ADD PRIMARY KEY (`espacio_id`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`), ADD KEY `estado_id` (`estado_id`), ADD KEY `espacio_id` (`espacio_id`), ADD KEY `servicio_id` (`servicio_id`), ADD KEY `dependencia_id` (`dependencia_id`);

--
-- Indices de la tabla `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_espacio`
--
ALTER TABLE `tipo_espacio`
  ADD PRIMARY KEY (`tipo_espacio_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignacion_solicitud`
--
ALTER TABLE `asignacion_solicitud`
  MODIFY `asignacion_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `dependencia`
--
ALTER TABLE `dependencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `edificio`
--
ALTER TABLE `edificio`
  MODIFY `edificio_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `espacio`
--
ALTER TABLE `espacio`
  MODIFY `espacio_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de la tabla `status`
--
ALTER TABLE `status`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipo_espacio`
--
ALTER TABLE `tipo_espacio`
  MODIFY `tipo_espacio_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=66;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignacion_solicitud`
--
ALTER TABLE `asignacion_solicitud`
ADD CONSTRAINT `fk_asignacion_solicitud_estado` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`),
ADD CONSTRAINT `fk_asignacion_solicitud_solicitud` FOREIGN KEY (`solicitud_id`) REFERENCES `solicitud` (`id`),
ADD CONSTRAINT `fk_asignacion_solicitud_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
ADD CONSTRAINT `fk_espacior_id` FOREIGN KEY (`espacio_id`) REFERENCES `espacio` (`espacio_id`),
ADD CONSTRAINT `fk_estado_id` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`),
ADD CONSTRAINT `fk_servicio_id` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`),
ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencia` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
