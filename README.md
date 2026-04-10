# 🚀 Resume.ai

Backend inteligente para **análise automática de currículos com Inteligência Artificial**, projetado para avaliar candidatos, extrair informações relevantes e gerar insights úteis para recrutamento.

---

## 📌 Sobre o projeto

O **Resume.ai** é uma aplicação backend desenvolvida em **Java com Spring Boot** que automatiza o processo de análise de currículos utilizando IA.

A proposta é transformar currículos não estruturados em **dados organizados e analisáveis**, permitindo:

- 📄 Extração de informações relevantes  
- 🧠 Geração de resumos inteligentes  
- 🎯 Avaliação de aderência a vagas  
- ⚡ Processamento assíncrono para alta performance  
- 📊 Disponibilização dos resultados via API REST  

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas, com separação clara de responsabilidades:

- **Controller:** Exposição dos endpoints REST  
- **Service:** Regras de negócio e processamento  
- **DTO:** Transferência de dados entre camadas  
- **Entity:** Modelagem do domínio  
- **Repository:** Acesso ao banco de dados  

### Tecnologias utilizadas

- ☕ Java 21  
- 🌱 Spring Boot  
- 🔗 REST API  
- 🧠 Integração com IA (LLM, ex: OpenAI)  
- 🗄️ Banco de dados relacional  
- ⚙️ Maven  

---

## ⚙️ Funcionalidades

- ✅ Upload e processamento de currículos  
- ✅ Execução assíncrona de análise com IA  
- ✅ Extração de dados estruturados  
- ✅ Geração de resumo automático  
- ✅ Controle de status do processamento:
  - `PENDING`
  - `PROCESSING`
  - `DONE`
- ✅ Medição de tempo de execução da IA  
- ✅ API para consulta dos resultados  

---

## 📂 Estrutura do projeto

src/main/java/io/github/guilhermelimadev/resume/ai/
├── controller/
├── service/
├── dto/
├── entity/
├── repository/
└── Application.java

---

## 🚀 Como rodar o projeto

### 🔹 1. Clonar o repositório

```bash
git clone https://github.com/GuilhermeLimaDev/resume.ai.git
cd resume.ai
