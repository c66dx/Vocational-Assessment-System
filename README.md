# 🎯 Test Vocacional - Orientación Profesional

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-2.0-brightgreen.svg)](https://github.com/tu-usuario/testVocacional)

Una aplicación de escritorio moderna y profesional desarrollada en Java que ayuda a los usuarios a descubrir sus áreas de interés profesional mediante un test vocacional interactivo, intuitivo y visualmente atractivo.

## ✨ Características Principales

### 🎨 **Interfaz Moderna**
- **Diseño profesional** con paleta de colores moderna (Bootstrap-inspired)
- **Tipografía Segoe UI** para una experiencia visual premium
- **Efectos hover** y transiciones suaves en todos los componentes
- **Diseño responsivo** que se adapta al contenido

### 📊 **Sistema de Evaluación Avanzado**
- **20 preguntas** cuidadosamente seleccionadas
- **5 áreas vocacionales** principales:
  - 🎨 **Arte y Creatividad** - Diseño, artes plásticas, multimedia
  - 👥 **Ciencias Sociales** - Psicología, educación, derecho
  - 💼 **Económica-Administrativa** - Negocios, finanzas, marketing
  - 🔬 **Ciencia y Tecnología** - Ingeniería, informática, investigación
  - 🌱 **Ciencias Ecológicas y de la Salud** - Medicina, biología, ambiente

### 🚀 **Experiencia de Usuario**
- **Barra de progreso visual** que muestra el avance del test
- **Navegación intuitiva** con botones Anterior/Siguiente
- **Resultados detallados** con tarjetas interactivas
- **Cálculo de porcentajes** preciso por área
- **Diálogo de profesiones** moderno y organizado

## 🎯 Capturas de Pantalla

| Pantalla de Bienvenida | Evaluación Interactiva | Resultados Profesionales |
|:--:|:--:|:--:|
| ![Bienvenida](screenshots/screenshot1.png) | ![Evaluación](screenshots/screenshot2.png) | ![Resultados](screenshots/screenshot3.png) |
| *Pantalla de inicio con instrucciones claras* | *Sistema de evaluación con barra de progreso* | *Resultados detallados con tarjetas interactivas* |

## 🚀 Inicio Rápido

### Prerrequisitos

- ☕ **Java Development Kit (JDK) 11 o superior**
- 🖥️ **Sistema operativo**: Windows, macOS o Linux

### 📥 Instalación y Ejecución

#### Opción 1: Scripts Automatizados (Recomendado)

**Windows:**
```batch
# Ejecutar directamente
run.bat

# O compilar por separado
compile.bat
```

**Linux/macOS:**
```bash
# Dar permisos y ejecutar
chmod +x run.sh
./run.sh

# O compilar por separado
chmod +x compile.sh
./compile.sh
```

#### Opción 2: Manual

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/testVocacional.git
   cd testVocacional
   ```

2. **Compilar el proyecto**
   ```bash
   mkdir bin
   javac -d bin -cp src src/Controlador/TestVocationalGUI.java
   ```

3. **Ejecutar la aplicación**
   ```bash
   java -cp bin Controlador.TestVocationalGUI
   ```

## 📖 Guía de Uso

### 🎯 **Proceso del Test**

1. **🚀 Inicio**: Lee las instrucciones y haz clic en "Comenzar Test"
2. **❓ Evaluación**: 
   - Responde cada pregunta con "Me gusta" 👍 o "No me gusta" 👎
   - Observa la barra de progreso para ver tu avance
   - Navega libremente entre preguntas
3. **📊 Resultados**: 
   - Visualiza tus áreas de mayor afinidad
   - Revisa el porcentaje de compatibilidad por área
   - Haz clic en cada área para ver profesiones recomendadas
4. **🔁 Reiniciar**: Opción para realizar el test nuevamente

### 💡 **Interpretación de Resultados**

- **Porcentajes altos (70%+)**: Áreas de fuerte afinidad vocacional
- **Porcentajes medios (40-70%)**: Áreas de interés moderado
- **Porcentajes bajos (<40%)**: Áreas de menor compatibilidad

## 🏗️ Arquitectura del Proyecto

```
src/
├── 🎮 Controlador/
│   ├── TestVocationalGUI.java      # 🖥️ Interfaz principal y lógica de UI
│   └── TestVocationalLogic.java    # 🧠 Lógica de negocio y cálculos
├── 📊 Modelo/
│   ├── Area.java                   # 🏷️ Modelo de áreas vocacionales
│   ├── Profession.java             # 👔 Modelo de profesiones
│   └── Question.java               # ❓ Modelo de preguntas
├── 🖼️ Vista/
│   └── ResultsGUI.java             # 📈 Interfaz de resultados
└── 📄 questions.txt                # 📚 Base de datos de preguntas

bin/                                # 📁 Archivos compilados
screenshots/                        # 📸 Capturas de pantalla
run.bat / run.sh                    # 🚀 Scripts de ejecución
```

### 🔧 Patrones de Diseño

- **MVC (Model-View-Controller)**: Separación clara de responsabilidades
- **Observer Pattern**: Para actualización reactiva de la interfaz
- **Factory Pattern**: Para creación de componentes UI reutilizables

## 🛠️ Tecnologías y Características Técnicas

| Tecnología | Propósito | Versión |
|------------|-----------|---------|
| ☕ **Java** | Lenguaje principal | 11+ |
| 🖼️ **Swing** | Interfaz gráfica | Nativo |
| 🎨 **Look & Feel** | Diseño moderno | Custom |
| 📁 **File I/O** | Gestión de datos | Nativo |

### 🎨 **Características de Diseño**

- **Paleta de colores**: Bootstrap-inspired (#007BFF, #28A745, #6C757D)
- **Tipografía**: Segoe UI para Windows, San Francisco para macOS
- **Componentes**: Botones con efectos hover, tarjetas con sombras
- **Animaciones**: Transiciones suaves entre pantallas

## 📊 Algoritmo de Evaluación

### 🔢 **Cálculo de Puntuaciones**

1. **Puntuación por pregunta**: +1 por respuesta positiva, 0 por negativa
2. **Puntuación por área**: Suma de todas las respuestas positivas del área
3. **Porcentaje**: (Puntos obtenidos / Total preguntas del área) × 100
4. **Ranking**: Ordenamiento descendente por puntuación

### 📈 **Ejemplo de Cálculo**

```
Área: Ciencia y Tecnología
Preguntas del área: 4
Respuestas positivas: 3
Porcentaje: (3/4) × 100 = 75%
```

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Para contribuir:

1. 🍴 **Fork** el repositorio
2. 🌿 **Crea** una rama (`git checkout -b feature/nueva-funcionalidad`)
3. 💾 **Commit** tus cambios (`git commit -m 'Añadir nueva funcionalidad'`)
4. 📤 **Push** (`git push origin feature/nueva-funcionalidad`)
5. 🔄 **Abre** un Pull Request

### 🎯 Roadmap y Mejoras Futuras

- [ ] 🌐 **Migración a JavaFX** para interfaz más moderna
- [ ] 🗄️ **Base de datos** SQLite para gestión avanzada
- [ ] 📱 **Versión web** con Spring Boot
- [ ] 📊 **Análisis estadístico** avanzado
- [ ] 🌍 **Soporte multiidioma** (EN, ES, PT)
- [ ] 📧 **Exportación de resultados** (PDF, JSON)
- [ ] 🔐 **Sistema de usuarios** y histórico
- [ ] 🤖 **IA para recomendaciones** personalizadas

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor y Reconocimientos

**Desarrollado con ❤️ por Tu Nombre**

- 🐙 [GitHub](https://github.com/tu-usuario)
- 💼 [LinkedIn](https://linkedin.com/in/tu-perfil)
- 📧 [Email](mailto:tu.email@ejemplo.com)

### 🙏 Agradecimientos

- Metodologías de orientación vocacional modernas
- Comunidad de desarrolladores Java
- Feedback de usuarios beta testers
- Inspiración en mejores prácticas de UX/UI

## 📊 Estadísticas del Proyecto

- **Líneas de código**: ~1,500+
- **Clases**: 6 principales
- **Métodos**: 50+ optimizados
- **Tiempo de desarrollo**: 2 semanas
- **Cobertura de pruebas**: En desarrollo

---

<div align="center">

**⭐ Si te gusta este proyecto, ¡dale una estrella! ⭐**

[🐛 Reportar Bug](https://github.com/tu-usuario/testVocacional/issues) • [💡 Solicitar Feature](https://github.com/tu-usuario/testVocacional/issues) • [💬 Discusiones](https://github.com/tu-usuario/testVocacional/discussions)

---

### 🚀 ¡Descubre tu vocación profesional hoy mismo!

</div>