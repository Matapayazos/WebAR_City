package ec.edu.ups.ar.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lugar.class)
public abstract class Lugar_ {

	public static volatile SingularAttribute<Lugar, String> codigo;
	public static volatile SingularAttribute<Lugar, Double> latitud;
	public static volatile SingularAttribute<Lugar, Double> longitud;
	public static volatile ListAttribute<Lugar, Imagen> imagenes;
	public static volatile SingularAttribute<Lugar, String> Descripcion;
	public static volatile SingularAttribute<Lugar, String> nombre;

}

