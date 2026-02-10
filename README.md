# AdMob Security Case Study – Secure Ad Integration

This project demonstrates the integration of **Google AdMob** (Banner and Interstitial ads) with a focus on **Privacy-Preserving Advertising** and **Third-Party SDK Hardening**. It explores the balance between monetization and application security.

---

## 🔍 Security Audit & SDK Governance

Integrating third-party advertising SDKs introduces significant risks. This project implements mitigations for common AdTech vulnerabilities.

### 1. Privacy & Data Protection (CCPA/GDPR Compliance)
* **Limited Data Tracking:** Configured the AdMob SDK to respect "Limit Ad Tracking" settings. 
* **Children's Privacy (COPPA):** Implemented `tagForChildDirectedTreatment` logic to ensure compliance with privacy regulations when applicable.
* **Consent Management:** Architecture ready for the **User Messaging Platform (UMP) SDK** to handle user consent for data collection securely.

### 2. Static Analysis & Supply Chain Security (MobSF)
* **SDK Sandboxing:** Audited the permissions required by the AdMob SDK to ensure they adhere to the **Principle of Least Privilege**.
* **Manifest Hardening:** Verified that AdMob-related activities are not improperly exported, preventing **Intent Spoofing**.
* **Obfuscation:** Applied specific **ProGuard/R8** rules to protect the integration logic while keeping the SDK functional.



---

## 🔐 Key Security Features

### 📡 Secure Traffic Analysis
- **TLS Enforcement:** All ad requests are forced through **HTTPS** via Network Security Configuration to prevent ad-injection and Man-in-the-Middle (MitM) attacks.
- **Traffic Monitoring:** Verified via **Burp Suite** that no PII (Personally Identifiable Information) is leaked through ad request parameters.

### 🛠️ Runtime & Logic Protection
- **Safe Interstitial Handling:** Implemented robust lifecycle management for full-screen ads to prevent **UI Redressing (Clickjacking)** where ads might be overlaid on sensitive app components.
- **Memory Safety:** Ads are loaded and cleared using lifecycle-aware components (Coroutines/StateFlow) to prevent memory leaks and background data consumption.

---

## ⚠️ Threat Model: AdTech Vectors

| Threat Vector | Risk | Mitigation |
| :--- | :--- | :--- |
| **Ad-ID (IDFA) Leakage** | Medium | User-level control over tracking and hardware-ID masking. |
| **Malicious Ad Payloads** | High | Relying on official Google SDK updates and keeping `targetSdkVersion` current for system-level protection. |
| **Sensitive Data Scraping** | Medium | Isolating Ad views from Activities containing sensitive user input/data. |
| **Insecure API Keys** | Low | AdMob App ID is stored in `manifest` but kept out of source control via `local.properties`. |

---

## 🛠️ Technical Stack
- **Language:** Kotlin & Coroutines
- **Monetization:** Google AdMob SDK
- **Architecture:** MVVM (Model-View-ViewModel)
- **Audit Tools:** MobSF, Burp Suite, App-Filter.

---

## 🚀 Installation & Security Review

1. **Clone the repo:**
   ```bash
   git clone [https://github.com/nikolaivetrik24062010/AdMobApp.git](https://github.com/nikolaivetrik24062010/AdMobApp.git)
Setup: Add your ADMOB_APP_ID to local.properties (do not commit this file).

Audit: Run a Release Build and inspect the APK using Jadx-GUI to verify that your business logic is separated from the advertising SDK logic.

🤝 Author
Nikolai Vetrik Senior Security Engineer & Mobile Developer 📧 devnikolaivetrik@gmail.com | 🔗 LinkedIn <a href="https://www.linkedin.com/in/nikolayvetrik24062010/" target="_blank">
