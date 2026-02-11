# AdMob Security Case Study – Secure SDK Integration

This project is a dedicated **Security Research & Development** case study focusing on the secure integration of the **Google AdMob SDK**. It explores the critical balance between mobile monetization and **Application Security (AppSec)**, specifically targeting third-party SDK hardening and user privacy.

---

## 🔍 Security Audit & SDK Governance

Integrating advertising SDKs introduces significant supply-chain risks. This project implements advanced mitigations for common AdTech vulnerabilities.

### 1. Privacy & Data Protection (CCPA/GDPR/COPPA)
* **Limited Data Tracking:** Programmatically configured the SDK to respect "Limit Ad Tracking" (LAT) headers and masked hardware identifiers.
* **Regulatory Compliance:** Implemented `tagForChildDirectedTreatment` to ensure strict adherence to **COPPA** and **GDPR** child-safety requirements.
* **Consent Management:** Architecture designed to support the **User Messaging Platform (UMP) SDK** for secure, encrypted user consent collection.

### 2. Supply Chain Security (MobSF & Static Analysis)
* **SDK Sandboxing:** Audited SDK permissions to enforce the **Principle of Least Privilege**, ensuring the ad library cannot access sensitive user components (Contacts, Location, etc.).
* **Manifest Hardening:** Verified all AdMob-related `Activities` are non-exported to prevent **Intent Spoofing** and unauthorized deep-linking.
* **Code Obfuscation:** Custom **R8/ProGuard** rules applied to strip sensitive metadata and protect integration logic from reverse engineering.



---

## 🔐 Key Security Features

### 📡 Secure Traffic Analysis
- **TLS Enforcement:** Global enforcement of **HTTPS** via `network_security_config.xml` to prevent Ad-Injection and Man-in-the-Middle (MitM) attacks.
- **Data Leakage Prevention:** Verified via **Burp Suite** proxying that no PII (Personally Identifiable Information) is transmitted via unencrypted or hidden ad request parameters.

### 🛠️ Runtime & Logic Protection
- **Anti-Clickjacking:** Implemented strict lifecycle management for Interstitial ads to prevent **UI Redressing**, ensuring ads do not obscure sensitive transaction buttons or input fields.
- **Memory Hygiene:** Leveraged **Kotlin Coroutines & StateFlow** to ensure ad objects are cleared from the heap upon UI destruction, preventing memory-resident data leakage.

---

## ⚠️ Threat Model: AdTech Vectors

| Threat Vector | Risk | Mitigation Strategy |
| :--- | :--- | :--- |
| **Ad-ID (IDFA) Leakage** | Medium | ID masking and user-controlled tracking toggles. |
| **Malicious Ad Payloads** | High | Using strict `targetSdkVersion` 34+ to leverage Android system-level sandbox updates. |
| **UI Redressing** | Medium | Hardware-synced indicators and lifecycle-aware Ad-Views. |
| **API Key Exposure** | Low | Segregated sensitive IDs from source control via `local.properties`. |

---

## 🛠️ Technical Stack
- **Language:** Kotlin & Coroutines
- **SDK:** Google AdMob (Google Play Services)
- **Architecture:** Clean Architecture + MVVM
- **AppSec Tools:** MobSF, Burp Suite, Jadx-GUI, Wireshark.

---

## 🚀 Installation & Audit Review

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/nikolaivetrik24062010/AdMobApp.git](https://github.com/nikolaivetrik24062010/AdMobApp.git)
2. Setup Credentials: Add your ADMOB_APP_ID to local.properties (this file is git-ignored to prevent credential leakage).

3. Run Security Audit: Build the Release variant and inspect the APK via Jadx-GUI to confirm that the business logic is obfuscated and isolated from the advertising SDK.
