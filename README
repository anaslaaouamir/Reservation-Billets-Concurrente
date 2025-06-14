# 🎟 Gestion Concurrente des Réservations de Billets

## 📌 Objectif du Projet

Ce projet a pour but de concevoir une *application robuste de réservation de billets* pour des événements.  
Développée avec *Spring Boot* (backend) et *HTML/CSS* (frontend), elle intègre des *mécanismes de gestion concurrente* afin de garantir :

- L'intégrité des données (pas de double réservation)
- Une meilleure réactivité utilisateur
- La capacité de gérer des réservations simultanées

---

## 🛑 Problèmes Identifiés

### 1. Double réservation  
Lors d’une forte affluence, deux utilisateurs peuvent tenter de réserver la *même place simultanément*, causant une incohérence des données.

### 2. Temps de réponse élevé  
La *génération du ticket* peut être lente et impacter négativement l’expérience utilisateur en ralentissant le traitement de la réservation.

---

## 🛠 Solutions mises en place

### 🔒 Verrouillage explicite (ReentrantLock)
Chaque tentative de réservation utilise un verrou associé au couple (idÉvénement, numéro de siège) pour *éviter les conflits d’accès concurrents*.

### 🚀 Traitement asynchrone (@Async)
La génération du ticket est réalisée *en tâche de fond*, ce qui permet de :
- Libérer immédiatement le thread utilisateur
- Réduire le temps de réponse perçu
- Optimiser la fluidité de l’expérience

---

## 🧱 Architecture du Projet

### 🧩 Services Métiers
- utilisateur-service : gestion des utilisateurs
- evenement-service : création et gestion des événements
- reservation-service : gestion des réservations + génération des tickets

### ⚙ Services Techniques
- config-server : configuration centralisée des microservices
- discovery-server (Eureka) : enregistrement et découverte dynamique
- api-gateway : point d’entrée unique pour les appels front/backend

---

## 🔄 Communication entre Microservices

Les microservices échangent via *Spring Cloud OpenFeign*, permettant des appels REST internes simples, typés et maintenables.

---

## 💻 Interface Utilisateur

L’interface utilisateur (HTML/CSS intégrée dans Spring Boot) permet :
- 📅 Visualisation des événements disponibles
- 🎫 Réservation de billets
- 📂 Consultation des réservations personnelles
- 🛠 Vue d’administration pour voir toutes les réservations

---

## 🧪 Technologies Utilisées

| Catégorie         | Technologies                                                  |
|-------------------|---------------------------------------------------------------|
| Backend           | Spring Boot, Spring Cloud (Eureka, Config Server, Gateway)    |
| Communication     | Spring Cloud OpenFeign                                        |
| Frontend          | HTML, CSS (intégré)                                           |
| Architecture      | Microservices                                                 |
| Concurrence       | ReentrantLock, @Async                                     |
| Base de Données   | H2                                                            |

---

## ✅ Résultats Attendus

- ✅ *Aucune double réservation* grâce à la synchronisation
- ⚡ *Réactivité améliorée* via traitement asynchrone
- 📈 *Scalabilité assurée* pour plusieurs centaines d’utilisateurs simultanés

---

## 🏁 Conclusion

Ce projet démontre l’importance d’une *gestion concurrente rigoureuse* dans les systèmes critiques. Grâce à l'utilisation de :
- 🔒 verrous explicites (ReentrantLock)
- 🚀 exécution asynchrone (@Async)

Nous avons conçu une solution :
- 🔐 *Fiable*
- ⚡ *Réactive*
- 🌍 *Évolutive*



---

## 👤 Auteur
*Anas Laaouamir*   
*Hamza Sabouri*  
Master IDLD – Faculté des Sciences de Rabat  # 🎟 Gestion Concurrente des Réservations de Billets

## 📌 Objectif du Projet

Ce projet a pour but de concevoir une *application robuste de réservation de billets* pour des événements.  
Développée avec *Spring Boot* (backend) et *HTML/CSS* (frontend), elle intègre des *mécanismes de gestion concurrente* afin de garantir :

- L'intégrité des données (pas de double réservation)
- Une meilleure réactivité utilisateur
- La capacité de gérer des réservations simultanées

---

## 🛑 Problèmes Identifiés

### 1. Double réservation  
Lors d’une forte affluence, deux utilisateurs peuvent tenter de réserver la *même place simultanément*, causant une incohérence des données.

### 2. Temps de réponse élevé  
La *génération du ticket* peut être lente et impacter négativement l’expérience utilisateur en ralentissant le traitement de la réservation.

---

## 🛠 Solutions mises en place

### 🔒 Verrouillage explicite (ReentrantLock)
Chaque tentative de réservation utilise un verrou associé au couple (idÉvénement, numéro de siège) pour *éviter les conflits d’accès concurrents*.

### 🚀 Traitement asynchrone (@Async)
La génération du ticket est réalisée *en tâche de fond*, ce qui permet de :
- Libérer immédiatement le thread utilisateur
- Réduire le temps de réponse perçu
- Optimiser la fluidité de l’expérience

---

## 🧱 Architecture du Projet

### 🧩 Services Métiers
- utilisateur-service : gestion des utilisateurs
- evenement-service : création et gestion des événements
- reservation-service : gestion des réservations + génération des tickets

### ⚙ Services Techniques
- config-server : configuration centralisée des microservices
- discovery-server (Eureka) : enregistrement et découverte dynamique
- api-gateway : point d’entrée unique pour les appels front/backend

---

## 🔄 Communication entre Microservices

Les microservices échangent via *Spring Cloud OpenFeign*, permettant des appels REST internes simples, typés et maintenables.

---

## 💻 Interface Utilisateur

L’interface utilisateur (HTML/CSS intégrée dans Spring Boot) permet :
- 📅 Visualisation des événements disponibles
- 🎫 Réservation de billets
- 📂 Consultation des réservations personnelles
- 🛠 Vue d’administration pour voir toutes les réservations

---

## 🧪 Technologies Utilisées

| Catégorie         | Technologies                                                  |
|-------------------|---------------------------------------------------------------|
| Backend           | Spring Boot, Spring Cloud (Eureka, Config Server, Gateway)    |
| Communication     | Spring Cloud OpenFeign                                        |
| Frontend          | HTML, CSS (intégré)                                           |
| Architecture      | Microservices                                                 |
| Concurrence       | ReentrantLock, @Async                                     |
| Base de Données   | H2                                                            |

---

## ✅ Résultats Attendus

- ✅ *Aucune double réservation* grâce à la synchronisation
- ⚡ *Réactivité améliorée* via traitement asynchrone
- 📈 *Scalabilité assurée* pour plusieurs centaines d’utilisateurs simultanés

---

## 🏁 Conclusion

Ce projet démontre l’importance d’une *gestion concurrente rigoureuse* dans les systèmes critiques. Grâce à l'utilisation de :
- 🔒 verrous explicites (ReentrantLock)
- 🚀 exécution asynchrone (@Async)

Nous avons conçu une solution :
- 🔐 *Fiable*
- ⚡ *Réactive*
- 🌍 *Évolutive*



---

## 👤 Auteur
*Anas Laaouamir*   
*Hamza Sabouri*  
Master IDLD – Faculté des Sciences de Rabat
