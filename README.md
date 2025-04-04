**\# Reporte sobre el uso del método static en Java y C++**

---

### 🌟 **Introducción**
El método `static` en **Java** y **C++** permite definir funciones y variables que pertenecen a la **clase** en lugar de a sus **instancias**. En **Java**, los métodos `static` se utilizan para operaciones que no requieren acceso a instancias particulares de la clase. En **C++**, el modificador `static` se emplea para definir funciones que solo son visibles dentro del archivo fuente en el que se declaran, evitando **conflictos de nombres** y mejorando la **organización** del código. El equipo se dividio en equipos de 2 para poder realizar cada una de las modificaciones de las prácticas, mi compañero Ismael Aguilar Miranda trabajo con CLaudia López Nicol para la autorización de la práctica 3, La compañeroa Alondra De la O. Bernal trabajó con Axel Aguilar López Peregrino y por úlyimo Juan Martinez Berumen trabajo con García López Emilio

---

### 🎯 **Desarrollo**

#### ✅ **1. Uso del método static en el código de Java**
El archivo `JuegoAdivinanzaAlternativo.java` incluye varios métodos **estáticos**:

```java
private static void iniciarJuego() { ... }
private static void imprimirInstrucciones() { ... }
private static int solicitarNumero(Scanner scanner) { ... }
private static String evaluarIntento(int numeroIngresado, int numeroSecreto) { ... }
private static boolean juegoTerminado(String resultado, int intentosRealizados) { ... }
```

🟢 **Explicación:** Estos métodos `static` no dependen de una instancia específica de la clase `JuegoAdivinanzaAlternativo`, lo que permite modularizar el código y mejorar su eficiencia.

---

#### 🔵 **2. Uso del método static en el código de C++**
El archivo `codigo 1.cpp` contiene varias funciones con la palabra clave `static`:

```cpp
static void agregarContacto(Contacto **lista, const char *nombre, const char *telefono, const char *correo);
static void eliminarContacto(Contacto **lista, const char *nombre);
static void mostrarContactos(Contacto *lista);
static void buscarContacto(Contacto *lista);
static void cargarContactos(Contacto **lista, int *contador, const char *filename);
```

🔷 **Explicación:** En **C++**, `static` restringe la visibilidad de estas funciones al archivo en el que están definidas. Esto evita **conflictos de nombres** y mejora la organización del código.

---

### 🌟 **Conclusión**
📌 **Java** y **C++** utilizan `static` para definir métodos y funciones que no dependen de instancias específicas.
📌 En **Java**, se usa para evitar la creación innecesaria de objetos.
📌 En **C++**, ayuda a restringir el alcance de las funciones dentro de un archivo fuente.

✅ **El uso adecuado de `static` mejora la modularidad y eficiencia del código en ambos lenguajes.** 🚀


