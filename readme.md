# 🎟️ Gestion Concurrente des Réservations de Billets

## 📘 Contexte

Dans les systèmes de réservation d’événements, plusieurs défis peuvent compromettre leur fiabilité, notamment :

- ❌ La **double réservation**  d’une même place, causée par des accès simultanés,
- 🐢 La **surcharge du système**, provoquant des lenteurs et une mauvaise expérience utilisateur

---

## 🎯 Objectif du Projet

Ce projet vise à concevoir une application de réservation de billets robuste, développée avec **Spring Boot** pour le backend et une interface **HTML/CSS** pour le frontend.

Il intègre des **mécanismes de programmation concurrente** pour éviter les conflits d’accès et garantir une meilleure fluidité.

---

## ⚠️ Problèmes Identifiés

### 1. Double réservation

Lorsqu’un grand nombre d’utilisateurs tentent de réserver la **même place pour un même événement au même moment**, il existe un risque que deux réservations soient validées en parallèle avant qu’une soit enregistrée en base de données, ce qui conduit à une incohérence (même place réservée deux fois).

### 2. Temps de réponse long lors de la génération du ticket

Après la validation d'une réservation, la **génération du ticket** peut prendre du temps. Ce délai peut entraîner une attente inutile pour l'utilisateur, voire un doute sur la réussite de la réservation.

---

## ✅ Solutions Mises en Place

### 🔒 1. Mécanisme de verrouillage (Lock)

Chaque tentative de réservation d’un siège est associée à une **clé de verrouillage unique** (basée sur l’événement et le numéro de siège).

Grâce à un **gestionnaire de verrous** (`ReentrantLock`), une seule réservation peut accéder à une place à un instant donné. Ainsi, toute tentative concurrente est temporairement bloquée jusqu’à la libération du verrou, garantissant que **chaque siège ne puisse être réservé qu’une seule fois.**

### ⚙️ 2. Exécution asynchrone de la génération des tickets

La génération des tickets est exécutée **en arrière-plan** (`@Async`), cela permet :

- ✅De valider rapidement la réservation côté utilisateur,
- ✅ De libérer les ressources pour traiter d’autres requêtes sans attendre la fin de la génération du ticket,
- ✅ D'améliorer l'expérience utilisateur en réduisant les temps de réponse apparents.

---

## 📈 Résultats Attendus

- ✔️ Intégrité des réservations assurée même en cas de forte concurrence.
- ⚡ Réactivité optimisée grâce à l’asynchronisme, avec une génération de ticket fluide en tâche de fond.
- 🧠 Un système capable de gérer des centaines de réservations simultanées sans conflits majeurs.

---

## 🧱 Architecture du Projet
Le système est organisé en deux grandes catégories de services :

### 🔧 Services Métiers

- **Service Utilisateur** : Gère l’enregistrement, la consultation et la gestion des utilisateurs.
- **Service Événement** : Permet l’ajout, la consultation et la gestion des événements disponibles pour réservation.
- **Service Réservation** : Centralise la logique de réservation des billets, y compris la gestion des sièges, la validation des réservations, et la génération des tickets.

### ⚙️ Services Techniques

- **Config Server** : Centralise les fichiers de configuration des services pour faciliter la gestion et la cohérence de l’environnement.
- **Eureka Discovery Server** : Permet l’enregistrement automatique des services et facilite leur communication dynamique entre eux.
- **API Gateway** : Sert d’unique point d’entrée au système. Elle permet de router les requêtes vers les microservices appropriés, tout en appliquant des politiques de sécurité, de journalisation, ou de gestion de trafic.

---

## 🔄 Communication entre Microservices

La communication entre les différents services métiers se fait de manière déclarative à l’aide de  **Spring Cloud OpenFeign**, ce qui simplifie les appels HTTP inter-services tout en améliorant la lisibilité du code.

---

## 🖥️ Interface Utilisateur

L’interface utilisateur est développée en **HTML et CSS**, intégrée directement dans le projet Spring Boot. Elle permet aux utilisateurs de :

- 📅 Consulter la liste des événements
- 🎫 Réserver un billet pour un événement
- 👤 Visualiser les réservations et tickets d’un utilisateur
- 🛠 Visualiser toutes les réservations en tant qu’administrateur

---

## 💻 Technologies Utilisées

| Élément                  | Technologies utilisées                                                      |
|--------------------------|----------------------------------------------------------------------------|
| **Backend**              | Spring Boot, Spring Cloud (Eureka, Config Server, Gateway), JPA, REST     |
| **Frontend**             | HTML, CSS (intégré dans Spring Boot)                                      |
| **Architecture**         | Microservices                                                             |
| **Gestion Concurrence**  | `ReentrantLock`, exécution asynchrone avec `@Async`                      |
| **Communication Interne**| Spring Cloud OpenFeign                                                    |
| **Base de données**      | H2                             |

---

## 🧾 Conclusion

Ce projet met en évidence l'importance de la **gestion concurrentedans les applications critiques**  notamment celles liées  **réservation en temps réel**.

En combinant :

- 🔒 des **verrous explicites** (`ReentrantLock`)
- ⚙️ des **tâches asynchrones** (`@Async`)

Nous avons construit une solution :

- ✅ Fiable  
- ⚡ Réactive  
- 📈 Scalable  
- 👨‍💻 Adaptée aux besoins modernes des utilisateurs

  ## 👤 Auteur
 [*Anas Laaouamir*](https://github.com/anaslaaouamir)
 
 [*Hamza Sabouri* ](https://github.com/hamzasabouri)
 
Master IDLD – Faculté des Sciences de Rabat
