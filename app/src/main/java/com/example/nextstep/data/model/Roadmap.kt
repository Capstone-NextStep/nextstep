package com.example.nextstep.data.model

data class Roadmap(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean
)

///rencana model data untuk mengambil data dari api
/*data class Result (
    val recommendCareer: List<RecommendCareer>
)

data class RecommendCareer (
    val career: String,
    val overview: String,
    val roadmaps: List<Roadmap>
)*/

//dummy data
val roadmapList = listOf(
    Roadmap(
        id = 1,
        title = "Network Basics",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = true
    ),
    Roadmap(
        id = 2,
        title = "Network Design",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = true
    ),
    Roadmap(
        id = 3,
        title = "Network Security",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = true
    ),
    Roadmap(
        id = 4,
        title = "Wireless Networking",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = true
    ),
    Roadmap(
        id = 5,
        title = "Network Troubleshooting",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = true
    ),
    Roadmap(
        id = 6,
        title = "Entreprise Networking",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = false
    ),
    Roadmap(
        id = 7,
        title = "Routing",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = false
    ),
    Roadmap(
        id = 8,
        title = "Switching",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = false
    ),
    Roadmap(
        id = 9,
        title = "Certification",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = false
    ),
    Roadmap(
        id = 10,
        title = "Cloud Networking",
        description = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking. This includes understanding what a network is, the types of networks (like LAN, MAN, WAN), the basics of internet protocols (like TCP/IP), and how data is handled",
        isDone = false
    ),
)