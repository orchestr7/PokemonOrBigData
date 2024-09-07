package ru.posidata.common

import ru.posidata.common.ResourceType.POKEMON
import ru.posidata.common.ResourceType.BIG_DATA

enum class ResourceType {
    POKEMON,
    BIG_DATA,
}

enum class Resources(
    val id: Int,
    val description: String,
    val type: ResourceType,
) {
    // Pokemons
    EEVEE(0, "A Normal-type Pokémon known for its ability to evolve into multiple different forms.", POKEMON),
    SQUIRTLE(1, "A Water-type Pokémon with a shell that it can hide in for protection.", POKEMON),
    MEOWTH(2, "A Normal-type Pokémon that loves shiny objects and is known for its ability to speak human language.", POKEMON),
    MACHOP(3, "A Fighting-type Pokémon with incredible strength, capable of lifting heavy objects.", POKEMON),
    KOFFING(4, "A Poison-type Pokémon that floats and emits toxic gases from its body.", POKEMON),
    POLIWAG(5, "A Water-type Pokémon with a swirl pattern on its belly, which is actually its intestines.", POKEMON),
    ABRA(6, "A Psychic-type Pokémon that sleeps 18 hours a day but can teleport away if threatened.", POKEMON),
    GEODUDE(7, "A Rock/Ground-type Pokémon that is often mistaken for a boulder.", POKEMON),
    GASTLY(8, "A Ghost/Poison-type Pokémon that is made of gas and can slip through any obstacle.", POKEMON),
    ONIX(9, "A Rock/Ground-type Pokémon with a long, rock-like body that tunnels underground.", POKEMON),
    CUBONE(10, "A Ground-type Pokémon that wears the skull of its deceased mother as a helmet.", POKEMON),
    CHANSEY(11, "A Normal-type Pokémon that carries a healing egg in its pouch.", POKEMON),
    STARYU(12, "A Water-type Pokémon with a core that glows when it is healthy.", POKEMON),
    SCYTHER(13, "A Bug/Flying-type Pokémon with sharp scythes for arms.", POKEMON),
    JYNX(14, "An Ice/Psychic-type Pokémon known for its dance-like movements and strange language.", POKEMON),
    GYARADOS(15, "A Water/Flying-type Pokémon that evolves from the weak Magikarp into a powerful and fearsome beast.", POKEMON),
    LAPRAS(16, "A Water/Ice-type Pokémon that ferries people across bodies of water.", POKEMON),
    DITTO(17, "A Normal-type Pokémon that can transform into any other Pokémon or object.", POKEMON),
    GRIMER(18, "A Poison-type Pokémon composed of sludge, which emits a foul stench.", POKEMON),
    PIDGEY(19, "A Normal/Flying-type Pokémon that is small, docile, and often found in the wild.", POKEMON),
    GOREBYSS(20, "A Water-type Pokémon known for its elegant appearance and its ability to drain bodily fluids from its prey.", POKEMON),
    ARBOK(21, "A Poison-type Pokémon known for its menacing hood pattern and powerful constriction abilities.", POKEMON),
    FEEBAS(22, "A Water-type Pokémon that is often seen as unattractive, but evolves into the beautiful Milotic.", POKEMON),

    // BigData
    HADOOP(23, "Framework that allows for the distributed processing of large data sets across clusters using programming models.", BIG_DATA),
    FLINK(24, "A stream processing framework that processes data in real time and is known for its low-latency performance.", BIG_DATA),
    HIVE(25, "A data warehouse infrastructure built on top of Hadoop, enabling users to perform queries on large datasets.", BIG_DATA),
    PIG(26, "A high-level platform for creating programs that run on Hadoop, using a language called Pig Latin.", BIG_DATA),
    SAMZA(27, "A distributed stream processing framework that integrates with Apache Kafka for data ingestion.", BIG_DATA),
    OOZIE(28, "A workflow scheduler system to manage Hadoop jobs, allowing users to define a sequence of actions to be executed.", BIG_DATA),
    IMPALA(29, "An open-source MPP SQL query engine for processing data stored in HDFS, known for its low-latency performance.", BIG_DATA),
    KINESIS(30, "A real-time data streaming service provided by AWS, used to collect, process, and analyze real-time data streams.", BIG_DATA),
    HUDI(31, "An open-source data lake platform providing streaming ingestion, ACID transactions, and incremental data processing.", BIG_DATA),
    AVRO(32, "A data serialization framework developed within Apache's Hadoop project that supports schema evolution.", BIG_DATA),
    SQOOP(33, "A tool for transferring bulk data between Apache Hadoop and structured datastores such as relational databases.", BIG_DATA),
    PHOENIX(34, "An open-source SQL query engine for Apache HBase that provides an SQL interface for interacting with HBase.", BIG_DATA),
    PANDAS(35, "An open-source data analysis and manipulation library for Python, widely used for data cleaning and transformation.", BIG_DATA),
    QUBOLE(36, "A cloud-native data platform that provides tools for data engineering and machine learning in a collaborative environment.", BIG_DATA),
    TRINO(37, "A distributed SQL query engine, formerly known as PrestoSQL, designed for running interactive queries on large datasets.", BIG_DATA),
    CEPH(38, "An open-source software-defined storage platform that provides object, block, and file storage in a unified system.", BIG_DATA),
    AMBARI(39, "An open-source management platform for provisioning, monitoring, and managing Hadoop clusters.", BIG_DATA),
    SEAHORSE(40, "A visual data science platform that enables users to create, manage, and deploy machine learning workflows.", BIG_DATA),
    ARVADOS(41, "An open-source platform for managing and analyzing large-scale genomic and biomedical data.", BIG_DATA),
    GEODE(42, "An open-source, distributed data management platform designed for high performance real-time data access and analytics.", BIG_DATA),
    ADABAS(43, "A high-performance, transactional database management system designed to handle large volumes of data.", BIG_DATA),
    ICEBERG(44, "An open table format for large analytic datasets that enables high-performance reads/writes and supports schema evolution.", BIG_DATA)    ;

    companion object {
        fun getById(i: Int): Resources = Resources.entries.find { it.id == i }!!
        fun getByName(name: String): Resources? = Resources.entries.find { it.name.uppercase() == name }
    }

    fun getName(): String {
        return name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}
