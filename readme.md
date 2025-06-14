Titre du Projet : Gestion Concurrente des Réservations de Billets

Dans les systèmes de réservation d’événements, plusieurs défis peuvent compromettre leur fiabilité, notamment :
•	La double réservation d’une même place, causée par des accès simultanés,
•	La surcharge du système, provoquant des lenteurs et une mauvaise expérience utilisateur.

Objectif du projet
Ce projet vise à concevoir une application de réservation de billets robuste, développée avec Spring Boot pour le backend, et une interface HTML/CSS pour le frontend, en intégrant des mécanismes de programmation concurrente afin d’éviter les conflits d’accès et de garantir une meilleure fluidité.

 Problèmes identifiés
1.	Les doubles réservations
o	Lorsqu’un grand nombre d’utilisateurs tentent de réserver la même place pour le même événement au même moment, il existe un risque que deux réservations soient validées en parallèle avant que l'une soit enregistrée en base de données, ce qui conduit à une incohérence (la même place réservée deux fois).
2.	Temps de réponse trop long lors de la génération du ticket
o	Après la validation d'une réservation, la génération du ticket peut prendre du temps. Ce délai peut entraîner une attente inutile pour l'utilisateur, voire un doute sur la réussite de la réservation.

Solutions mises en place
1. Utilisation d’un mécanisme de verrouillage (Lock)
Pour éviter la double réservation, chaque tentative de réservation d’un siège est associée à une clé de verrouillage unique (basée sur l’événement et le numéro de siège).
Grâce à un gestionnaire de verrous (via un ReentrantLock), une seule réservation peut accéder à une place à un instant donné. Ainsi, toute tentative concurrente est temporairement bloquée jusqu’à la libération du verrou, garantissant que chaque siège ne puisse être réservé qu’une seule fois.
2. Exécution asynchrone de la génération des tickets
La génération des tickets, qui peut être une opération lente, est exécutée en arrière-plan (de manière asynchrone). Cela permet :
•	De valider rapidement la réservation côté utilisateur,
•	De libérer les ressources pour traiter d’autres requêtes sans attendre la fin de la génération du ticket,
•	D'améliorer l'expérience utilisateur en réduisant les temps de réponse apparents.

Résultat attendu
•	Intégrité des réservations assurée même en cas de forte concurrence.
•	Réactivité optimisée grâce à l’asynchronisme, avec une génération de ticket fluide en tâche de fond.
•	Un système capable de gérer des centaines de réservations simultanées sans conflits majeurs.

Architecture du projet
Le système est organisé en deux grandes catégories de services :
1. Services métiers
•	Service Utilisateur : Gère l’enregistrement, la consultation et la gestion des utilisateurs.
•	Service Événement : Permet l’ajout, la consultation et la gestion des événements disponibles pour réservation.
•	Service Réservation : Centralise la logique de réservation des billets, y compris la gestion des sièges, la validation des réservations, et la génération des tickets.
2. Services techniques
•	Service de configuration (Config Server) : Centralise les fichiers de configuration des services pour faciliter la gestion et la cohérence de l’environnement.
•	Service de découverte (Eureka Discovery Server) : Permet l’enregistrement automatique des services et facilite leur communication dynamique entre eux.
•	API Gateway : Sert d’unique point d’entrée au système. Elle permet de router les requêtes vers les microservices appropriés, tout en appliquant des politiques de sécurité, de journalisation, ou de gestion de trafic.

Communication entre microservices
La communication entre les différents services métiers se fait de manière déclarative à l’aide de Spring Cloud OpenFeign, ce qui simplifie les appels HTTP inter-services tout en améliorant la lisibilité du code.



Interface utilisateur
L’interface utilisateur est développée en HTML et CSS, intégrée directement dans le projet Spring Boot. Elle permet aux utilisateurs de :
•	Consulter la liste des événements,
•	Réserver un billet pour un événement,
•	Visualiser les réservations et les tickets par utilisateur.
•	Visualiser les réservations par l’admin.

Résumé des technologies utilisées
•	Backend : Spring Boot, Spring Cloud (Eureka, Config Server, Gateway), OpenFeign, JPA, REST
•	Frontend : HTML, CSS (intégré dans Spring Boot)
•	Architecture : Microservices
•	Gestion concurrente : ReentrantLock et exécution asynchrone avec @Async
•	Base de données : (à préciser si vous utilisez MySQL, PostgreSQL, etc.)

Conclusion
Ce projet met en évidence l'importance de la gestion concurrente dans les applications critiques, notamment celles liées à la réservation en temps réel. En combinant des techniques telles que les verrous explicites et les tâches asynchrones, nous avons construit un système plus fiable, réactif et adapté aux besoins des utilisateurs modernes.

