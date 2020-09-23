package com.theta.handler;

import com.theta.model.ThetaParticle;
import com.theta.model.ThetaParticleSystemStatus;
import com.theta.util.Constants;

public class ThetaParticleHandler extends ThetaHandler<ThetaParticle> {

  /* The maximum amount of particles allowed in this system. */
  private final int MAX_PARTICLES;
  
  /* The number of alive particles in the backing-list. */
  private int aliveCount;
  
  public ThetaParticleHandler(int maxParticles) {
    super(maxParticles);
    this.MAX_PARTICLES = maxParticles;
    this.aliveCount = 0;
  }
  
  /**
   * 
   * @param p
   * @return
   */
  public ThetaParticleSystemStatus insertParticle(ThetaParticle p) {
    if (this.aliveCount >= this.MAX_PARTICLES) {
      return ThetaParticleSystemStatus.THETA_PS_FULL;
    }
    
    this.entities.add(p);
    this.aliveCount++;
    return ThetaParticleSystemStatus.THETA_PS_SUCCESS;
  }
  
  @Override
  public void update() {
    for (int i = 0; i < this.aliveCount; i++) {
      ThetaParticle particle = this.entities.get(i);
      
      if ((particle.getFlags() & Constants.DEATH_MASK) > 0) {
        int deadIndex = --(this.aliveCount);
        ThetaParticle backParticle = this.entities.get(deadIndex);
        
        ThetaParticle tmp = backParticle;
        backParticle = particle;
        particle = tmp;
      }
    }
  }
}
