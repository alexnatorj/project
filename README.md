# project Formulário Automatizado

This project, or better said, this framework was created to facilitate the create of forms in Java, in special J2SE. However,
since it is obeying design pattern (Abstract Factory), the forms can be implemented in any kind of Java GUI.

Basically, the model classes are implemented with anotations (see AnotacaoInterface), when implementing a GUI class, the class ExtratoDados
generates a Map. If you are extracting data from an Object, use the method ExtratorDados.extratorDadosToFormulario(obj). And, if you are
extracting data from a class use the method ExtratorDados.extratorDadosToFormularioClass(clazz). 

There is an example in the FormularioJavaFX. You can use this class as basis to create new models.

This framework is particularlly useful when you have subclasses with more fields and they need to be created accordingly.

I hope you find this usefull.
