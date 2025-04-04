#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Contacto {
    char nombre[50];
    char telefono[20];
    char correo[50];
    struct Contacto *siguiente;
} Contacto;

static void agregarContacto(Contacto **lista, const char *nombre, const char *telefono, const char *correo);
static void eliminarContacto(Contacto **lista, const char *nombre);
static void mostrarContactos(Contacto *lista);
static void buscarContacto(Contacto *lista);
static void cargarContactos(Contacto **lista, int *contador, const char *filename);

int main() {
    Contacto *lista = NULL;
    int totalContactos = 0, opcion;

    do {
        printf("\n1. Agregar contacto\n2. Mostrar contactos\n3. Eliminar contacto\n4. Cargar contactos\n5. Buscar contacto\n0. Salir\nSeleccione: ");
        scanf("%d", &opcion);
        getchar();

        switch(opcion) {
            case 1: {
                char nombre[50], telefono[20], correo[50];
                printf("Nombre: "); scanf(" %49[^\n]", nombre);
                printf("Tel�fono: "); scanf(" %19[^\n]", telefono);
                printf("Correo: "); scanf(" %49[^\n]", correo);
                agregarContacto(&lista, nombre, telefono, correo);
                totalContactos++;
                break;
            }
            case 2: mostrarContactos(lista); break;
            case 3: {
                char nombre[50];
                printf("Eliminar: "); scanf(" %49[^\n]", nombre);
                eliminarContacto(&lista, nombre);
                totalContactos--;
                break;
            }
            case 4: cargarContactos(&lista, &totalContactos, "contactos.txt"); break;
            case 5: buscarContacto(lista); break;
        }
    } while(opcion != 0);

    return 0;
}

static void agregarContacto(Contacto **lista, const char *nombre, const char *telefono, const char *correo) {
    Contacto *nuevo = (Contacto *)malloc(sizeof(Contacto));
    if (!nuevo) return;
    strncpy(nuevo->nombre, nombre, sizeof(nuevo->nombre) - 1);
    strncpy(nuevo->telefono, telefono, sizeof(nuevo->telefono) - 1);
    strncpy(nuevo->correo, correo, sizeof(nuevo->correo) - 1);
    nuevo->nombre[sizeof(nuevo->nombre) - 1] = '\0';
    nuevo->telefono[sizeof(nuevo->telefono) - 1] = '\0';
    nuevo->correo[sizeof(nuevo->correo) - 1] = '\0';
    nuevo->siguiente = *lista;
    *lista = nuevo;
}

static void eliminarContacto(Contacto **lista, const char *nombre) {
    Contacto *actual = *lista, *anterior = NULL;
    while (actual && strcmp(actual->nombre, nombre) != 0) {
        anterior = actual;
        actual = actual->siguiente;
    }
    if (actual) {
        if (!anterior) *lista = actual->siguiente;
        else anterior->siguiente = actual->siguiente;
        free(actual);
    }
}

static void mostrarContactos(Contacto *lista) {
    int i = 1;
    while (lista) {
        printf("%d. %s | %s | %s\n", i++, lista->nombre, lista->telefono, lista->correo);
        lista = lista->siguiente;
    }
}

static void buscarContacto(Contacto *lista) {
    char nombre[50];
    printf("Buscar: "); scanf(" %49[^\n]", nombre);
    while (lista) {
        if (strcmp(lista->nombre, nombre) == 0) {
            printf("%s | %s | %s\n", lista->nombre, lista->telefono, lista->correo);
            return;
        }
        lista = lista->siguiente;
    }
    printf("No encontrado.\n");
}

static void cargarContactos(Contacto **lista, int *contador, const char *filename) {
    FILE *archivo = fopen(filename, "r");
    if (!archivo) return;
    char nombre[50], telefono[20], correo[50];
    int cargados = 0;
    while (fscanf(archivo, "%49s %19s %49s", nombre, telefono, correo) == 3) {
        agregarContacto(lista, nombre, telefono, correo);
        cargados++;
    }
    fclose(archivo);
    *contador += cargados;
}

