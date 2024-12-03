package com.example.nextstep.data.model

data class Roadmap(
    val id: Int, //id tidak dipakai
    val title: String,
    val description: String, // description tidak dipakai
    val isDone: Boolean
)

data class Career(
    val id: Int,
    val title: String,
    val overview: String,
    val roadmaps: List<Roadmap>
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

//dummy data roadmap
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

//dummy data career
val careerList = listOf(
    Career(
        id = 1,
        title = "Software Engineer",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 2,
        title = "Data Analyst",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 3,
        title = "Network Engineer",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 4,
        title = "Cloud Architect",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 5,
        title = "Cybersecurity Analyst",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 6,
        title = "IT Project Manager",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 7,
        title = "Data Scientist",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 8,
        title = "DevOps Engineer",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 9,
        title = "IT Support Analyst",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
    Career(
        id = 10,
        title = "UX/UI Designer",
        overview = "The Network Basics section in the Becoming a Network Engineer roadmap introduces the fundamental concepts of computer networking.",
        roadmaps = roadmapList
    ),
)