object Breed extends Enumeration {
  type Breed = Value
  val doberman = Value("Doberman Pinscher")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
  val dane = Value("Great Dane")
  val portie = Value("Portuguese Water Dog")
}

import Breed._

println("ID\tBreed")
for (breed <- Breed.values) println(s"${breed.id}\t${breed}")
Breed.values.filter(_.toString.endsWith("Terrier")).foreach(println _)
