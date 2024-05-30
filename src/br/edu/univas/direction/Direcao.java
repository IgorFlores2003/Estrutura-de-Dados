package br.edu.univas.direction;

	public enum Direcao {
	    LESTE, OESTE, NORTE, SUL;

	    public Direcao opposite() {
	        switch (this) {
	            case LESTE: return OESTE;
	            case OESTE: return LESTE;
	            case NORTE: return SUL;
	            case SUL: return NORTE;
	            default: throw new IllegalArgumentException();
	        }
	    }
	}

