# PruebaParcial1BIM
## Nombre de Herramienta:  Gemini
## Tipo de cuenta: Gratuita
## Prompts: 
 - Se utilizo un único propmt para verificar el error del programa, puesto que contaba mal las palabras encontradas en la matriz, el prompt fue:

"Necesito que me ayudes a verificar el funcionamiento, calidad y optimizacion de mi codigo, el problema a resolver se trataba de a traves de hilos, contar cuantas veces existe una palabra dentro de una matriz, se tiene que resolver de forma paralelo, es decir, no secuencial, y manejar bien las excepciones, dime que partes deberia mejorar de mi codigo y por que esta mal o puede estar mejor."

- Ante la solicitud, la respuesta fue que el error ocurría principalmente en la variable contadorLocal del método run(), en el codigo del branch sin-ia esta variable fue inicializada con 1, y este fue el error, ya que, aunque la palabra no aparezca el contador aumentara a 1, y si existe aumentara a 2, entonces, la correción es inicializar esta variable con 0.
- Otra respuesta como forma de mejorar la eficiencia del código, fue reemplazar el uso de AtomicInteger, por LongAdder, puesto que este ultimo es mas eficientes cuando se trata de sumatorias en paralelo.
