package com.myhome.designpattern.specification.genericspec;

/**
 * Abstract base implementation of composite
 * {@link com.myhome.designpattern.specification.genericspec.genericspec.Specification} with default
 * implementations for {@code and}, {@code or} and {@code not}.
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

	/**
	 * {@inheritDoc}
	 */
	public abstract boolean isSatisfiedBy(T t);

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> or(final Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}

	/**
	 * {@inheritDoc}
	 */
	public Specification<T> not(final Specification<T> specification) {
		return new NotSpecification<T>(specification);
	}
	
}

/**
 * Voici un exemple de patron de documentation pour une règle métier :<br/>
 * Nom Nom de la classe qui correspond à la classe en Java (donc sans d'accent,
 * sans espace, et qui ne commence pas par un chiffre)<br/>
 * Cas d'utilisation Où on utilise cette règle<br/>
 * Dépendance Il est possible de combiner les règles. Une règle peut être
 * simplement une combinaison d'autres règles<br/>
 * Données en entrée Quelles sont les données analysées<br/>
 * Description & algorithme métier Décrire le plus précisément possible la règle
 * métier (à la valeur près)<br/>
 * Configurabilité Indiquer si la règle est modifiable, activable, desactivable
 * à chaud<br/>
 * Les règles métiers ont intérêt à être classées par package, un package par
 * domaine fonctionnel.<br/>
 */






//
//
//down vote
//Inspired by a comment some days ago. Change
//
//bool IsSatisfiedBy(T entity);
//to
//
//Result IsSatisfiedBy(T entity);
//
//public class Result 
//{
//    public boolean IsSatisfied{}
//    public List<String> message() {}
//}
//But you have to implement && , ! and || :
//
//&& Result r1 = spec1.satisfied(o);
//    if (r1.isSatisfied()) {
//        Result r2 = spec2.satisfied(o);
//        if (r2.isSatisfied()) {
//            return new Result();
//        } else {
//            return r2;
//        }
//    } else {
//        return r1;
//    }
//
// || Result r1 = spec1.satisfied(o);
//    if (r1.isSatisfied()) {
//        return new Res();           
//    } else {
//        Result r2 = spec2.satisfied(o);
//        if (r2.isSatisfied()) {
//            return new Result();
//        } else {
//            return r2.append(r1.message());
//        }
//    }