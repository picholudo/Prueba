package com.mycompany.myapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.CodigoEstudio;
import com.mycompany.myapp.domain.EdadTipoPrueba;
import com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma;
import com.mycompany.myapp.domain.EstadisticaFAB;
import com.mycompany.myapp.domain.EstadisticaMMSE;
import com.mycompany.myapp.domain.EstadisticaPuntoCorte;
import com.mycompany.myapp.domain.EstadisticaPzNeuronorma;
import com.mycompany.myapp.domain.EstadisticaSSNeuronorma;
import com.mycompany.myapp.domain.EstadisticaTAVEC;
import com.mycompany.myapp.domain.EstadisticaTBA;
import com.mycompany.myapp.domain.Informe;
import com.mycompany.myapp.domain.Paciente;
import com.mycompany.myapp.domain.Prueba;
import com.mycompany.myapp.domain.ResultadoPrueba;
import com.mycompany.myapp.domain.ResultadoPrueba.ResultadoPuntoCorte;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;
import com.mycompany.myapp.repository.CodigoEstudioRepository;
import com.mycompany.myapp.repository.EdadTipoPruebaRepository;
import com.mycompany.myapp.repository.EstadisticaAjusteNeuronormaRepository;
import com.mycompany.myapp.repository.EstadisticaFABRepository;
import com.mycompany.myapp.repository.EstadisticaMMSERepository;
import com.mycompany.myapp.repository.EstadisticaPuntoCorteRepository;
import com.mycompany.myapp.repository.EstadisticaPzNeuronormaRepository;
import com.mycompany.myapp.repository.EstadisticaSSNeuronormaRepository;
import com.mycompany.myapp.repository.EstadisticaTAVECRepository;
import com.mycompany.myapp.repository.EstadisticaTBARepository;
import com.mycompany.myapp.repository.InformeRepository;
import com.mycompany.myapp.repository.PruebaRepository;
import com.mycompany.myapp.service.CalculadorResultadoPrueba;
import com.mycompany.myapp.service.CalculadorResultadoPruebaException;

@Service
@Transactional
public class CalculadorResultadoPruebaImpl implements CalculadorResultadoPrueba {
	
	
	private final Logger log = LoggerFactory.getLogger(CalculadorResultadoPruebaImpl.class);
	
	private final PruebaRepository pruebaRepository;
	private final InformeRepository informeRepository;
	private final CodigoEstudioRepository codigoEstudioRepository;
	private final EdadTipoPruebaRepository edadTipoPruebaRepository;
	private final EstadisticaMMSERepository estadisticaMMSERepository;
	private final EstadisticaSSNeuronormaRepository estadisticaSSNeuronormaRepository;
	private final EstadisticaAjusteNeuronormaRepository estadisticaAjusteNeuronormaRepository;
	private final EstadisticaPzNeuronormaRepository estadisticaPzNeuronormaRepository;
	private final EstadisticaFABRepository estadisticaFABRepository;
	private final EstadisticaTAVECRepository estadisticaTAVECRepository;
	private final EstadisticaTBARepository estadisticaTBARepository;
	private final EstadisticaPuntoCorteRepository estadisticaPuntoCorteRepository;
	
	
	public CalculadorResultadoPruebaImpl(
			PruebaRepository pruebaRepository,
			InformeRepository informeRepository,
			CodigoEstudioRepository codigoEstudioRepository,
			EdadTipoPruebaRepository edadTipoPruebaRepository,
			EstadisticaMMSERepository estadisticaMMSERepository,
			EstadisticaSSNeuronormaRepository estadisticaSSNeuronormaRepository,
			EstadisticaAjusteNeuronormaRepository estadisticaAjusteNeuronormaRepository,
			EstadisticaPzNeuronormaRepository estadisticaPzNeuronormaRepository,
			EstadisticaFABRepository estadisticaFABRepository,
			EstadisticaTAVECRepository estadisticaTAVECRepository,
			EstadisticaTBARepository estadisticaTBARepository,
			EstadisticaPuntoCorteRepository estadisticaPuntoCorteRepository
			) {
		this.pruebaRepository = pruebaRepository;
		this.informeRepository = informeRepository;
		this.codigoEstudioRepository = codigoEstudioRepository;
		this.edadTipoPruebaRepository = edadTipoPruebaRepository;
		this.estadisticaMMSERepository = estadisticaMMSERepository;
		this.estadisticaAjusteNeuronormaRepository = estadisticaAjusteNeuronormaRepository;
		this.estadisticaSSNeuronormaRepository = estadisticaSSNeuronormaRepository;
		this.estadisticaPzNeuronormaRepository = estadisticaPzNeuronormaRepository;
		this.estadisticaFABRepository = estadisticaFABRepository;
		this.estadisticaTAVECRepository = estadisticaTAVECRepository;
		this.estadisticaTBARepository = estadisticaTBARepository;
		this.estadisticaPuntoCorteRepository = estadisticaPuntoCorteRepository;
	}
	
	
	/* (non-Javadoc)
	 * @see com.mycompany.myapp.service.CalculadorResultadoPrueba#calcular(com.mycompany.myapp.domain.ResultadoPrueba)
	 */
	@Override
	public ResultadoPrueba calcular(final ResultadoPrueba resultadoPrueba) throws CalculadorResultadoPruebaException {
		
		log.debug("ResultadoPrueba = {}", resultadoPrueba);
		
		if(	resultadoPrueba == null) {
			throw new IllegalArgumentException("ResultadoPrueba no puede ser null");
		}
		
		if(resultadoPrueba.getInforme() == null) {
			throw new IllegalArgumentException("ResultadoPrueba debe pertenecer a un Informe");
		}
		
		if(resultadoPrueba.getPd() == null) {
			throw new IllegalArgumentException("ResultadoPrueba requiere un valor para PD");
		}
		
		
		final Informe informe = informeRepository.getOne(resultadoPrueba.getInforme().getId());
		log.debug("Informe = {}", informe);
		
		final Paciente paciente = informe.getPaciente();
		log.debug("Paciente = {}", paciente);
		
		final Prueba prueba = pruebaRepository.getOne(resultadoPrueba.getPrueba().getId());
		log.debug("Prueba = {}", prueba);
		
		final TipoPrueba tipoPrueba = prueba.getTipoPrueba();
		
		EdadTipoPrueba etp = lookupEdadTipoPrueba(paciente, tipoPrueba);
		
		final CodigoEstudio codigoEstudio = codigoEstudioRepository.findByNivelEstudios(paciente.getEstudios());
		log.debug("CodigoEstudio = {}", codigoEstudio);
		if(codigoEstudio == null) {
			throw new CalculadorResultadoPruebaException("CodigoEstudio no encontrado para NivelEstudios {0}", paciente.getEstudios());
		}
		
		try {
			
			log.info("Inicio de cálculo de TipoPrueba = {}", tipoPrueba);
			
			Float pz;
			
			switch(tipoPrueba) {
			case MMSE:
				EstadisticaMMSE estadisticaMMSE = estadisticaMMSERepository.findByCodigoEstudioAndEdadTipoPrueba(codigoEstudio, etp);
				log.debug("EstadisticaMMSE = {}", estadisticaMMSE);
				if(estadisticaMMSE == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaMMSE no encontrada para CodigoEstudio {0} y EdadTipoPrueba {1}", codigoEstudio, etp);
				}
				pz = calcularPz(resultadoPrueba.getPd(), estadisticaMMSE.getMedia(), estadisticaMMSE.getDesviacionTipica());
				resultadoPrueba.setPz(pz);
				break;
				
			case NEURONORMA:
				EstadisticaSSNeuronorma estadisticaSSNeuronorma = estadisticaSSNeuronormaRepository.findByPruebaAndEdadTipoPrueba(prueba, etp);
				log.debug("EstadisticaSSNeuronorma = {}", estadisticaSSNeuronorma);
				if(estadisticaSSNeuronorma == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaSSNeuronorma no encontrada para Prueba {0} y EdadTipoPrueba {1}", prueba, etp);
				}
				
				EstadisticaAjusteNeuronorma estadisticaAjusteNeuronorma = estadisticaAjusteNeuronormaRepository.findByPruebaAndCodigoEstudioAndScaledScore(prueba, codigoEstudio, estadisticaSSNeuronorma.getScaledScore()); 
				log.debug("EstadisticaAjusteNeuronorma = {}", estadisticaAjusteNeuronorma);
				if(estadisticaAjusteNeuronorma == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaAjusteNeuronorma no encontrada para Prueba {0} , CodigoEstudio {1} y ScaledScore {2}", prueba, codigoEstudio, estadisticaSSNeuronorma.getScaledScore());
				}
				
				EstadisticaPzNeuronorma estadisticaPzNeuronorma = estadisticaPzNeuronormaRepository.findByAjusteEstudios(estadisticaAjusteNeuronorma.getAjusteEstudios());
				log.debug("EstadisticaPzNeuronorma = {}", estadisticaPzNeuronorma);
				if(estadisticaPzNeuronorma == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaPzNeuronorma no encontrada para  AjusteEstudios {0}", estadisticaAjusteNeuronorma.getAjusteEstudios());
				}
				
				resultadoPrueba.setPz(estadisticaPzNeuronorma.getPz());
				break;
				
			case FAB:
				EstadisticaFAB estadisticaFAB = estadisticaFABRepository.findByCodigoEstudioAndEdadTipoPrueba(codigoEstudio, etp);
				log.debug("EstadisticaFAB = {}", estadisticaFAB);
				if(estadisticaFAB == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaFAB no encontrada para CodigoEstudio {0} y EdadTipoPrueba {1}", codigoEstudio, etp);
				}
				pz = calcularPz(resultadoPrueba.getPd(), estadisticaFAB.getMedia(), estadisticaFAB.getDesviacionTipica());
				resultadoPrueba.setPz(pz);
				break;
				
			case TAVEC: 
				EstadisticaTAVEC estadisticaTAVEC = estadisticaTAVECRepository.findByPruebaAndEdadTipoPrueba(prueba, etp);
				log.debug("EstadisticaTAVEC = {}", estadisticaTAVEC);
				if(estadisticaTAVEC == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaTAVEC no encontrada para Prueba {0} y EdadTipoPrueba {1}", prueba, etp);
				}
				pz = calcularPz(resultadoPrueba.getPd(), estadisticaTAVEC.getMedia(), estadisticaTAVEC.getDesviacionTipica());
				resultadoPrueba.setPz(pz);
				break;
				
			case TBA: 
				EstadisticaTBA estadisticaTBA = estadisticaTBARepository.findByPruebaAndCodigoEstudioAndEdadTipoPrueba(prueba, codigoEstudio, etp);
				log.debug("EstadisticaTBA = {}", estadisticaTBA);
				if(estadisticaTBA == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaTBA no encontrada para Prueba {0}, CodigoEstudios {1} y EdadTipoPrueba {2}", prueba, codigoEstudio, etp);
				}
				pz = calcularPz(resultadoPrueba.getPd(), estadisticaTBA.getMedia(), estadisticaTBA.getDesviacionTipica());
				resultadoPrueba.setPz(pz);
				
				break;
				
			case PUNTOS_DE_CORTE:
				EstadisticaPuntoCorte estadisticaPuntoCorte = estadisticaPuntoCorteRepository.findByPrueba(prueba);  
				log.debug("EstadisticaPuntoCorte = {}", estadisticaPuntoCorte);
				if(estadisticaPuntoCorte == null) {
					throw new CalculadorResultadoPruebaException("EstadisticaPuntoCorte no encontrada para Prueba {0}", prueba);
				}				
				
				resultadoPrueba.setPz(calcularPz(resultadoPrueba.getPd(), estadisticaPuntoCorte.getPuntoCorte()));
				break;
			}
		
		} catch (CalculadorResultadoPruebaException e) {
			throw e;
		} 
		catch(Exception e) {
			throw new CalculadorResultadoPruebaException(e, "Error al calcular ResultadoPrueba");
		}
		
		return resultadoPrueba;
	}
	
	private EdadTipoPrueba lookupEdadTipoPrueba(Paciente paciente, TipoPrueba tipoPrueba) throws CalculadorResultadoPruebaException {
		EdadTipoPrueba etp = null;
		if(!TipoPrueba.PUNTOS_DE_CORTE.equals(tipoPrueba)) {
			etp = edadTipoPruebaRepository.findByTipoPruebaAndEdad(tipoPrueba, paciente.getEdad());
			log.debug("EdadTipoPrueba = {}", etp);
			if(etp == null) {
				throw new CalculadorResultadoPruebaException("EdadTipoPrueba no encontrada para tipoPrueba {0} y edad {1}", tipoPrueba, paciente.getEdad());
			}
		}
		
		return etp;
	}
	
	private Float calcularPz(Integer pd, Integer puntoCorte) {
		ResultadoPuntoCorte resultado;
		if(pd.equals(puntoCorte)) {
			resultado = ResultadoPuntoCorte.IGUAL;
		} else if(pd > puntoCorte) {
			resultado = ResultadoPuntoCorte.MAYOR;
		} else {
			resultado = ResultadoPuntoCorte.MENOR;
		}
		
		return resultado.getValor();
	}
	
	private Float calcularPz(Integer pd, Float media, Float dt) {
		Float pz = (pd - media) / dt; 
		log.debug("PZ = {} = ({} - {}) / {}", pz, pd, media, dt);
		return pz;
	}
}
