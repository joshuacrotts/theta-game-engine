package com.theta.handler;

import java.awt.Graphics2D;
import java.util.Collections;

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
    this.entities.add(this.aliveCount, p);
    this.aliveCount++;
    return ThetaParticleSystemStatus.THETA_PS_SUCCESS;
  }
  
  @Override
  public void update() {
    for (int i = 0; i < this.aliveCount; i++) {
      ThetaParticle particle = this.entities.get(i);
      particle.update();

      if ((particle.getFlags() & Constants.DEATH_MASK) != 0) {
        int deadIndex = (--this.aliveCount);
        ThetaParticle backParticle = this.entities.get(deadIndex);
        this.entities.set(deadIndex, particle);
        this.entities.set(i, backParticle);
        this.entities.remove(deadIndex);
      }
    }
  }
  
  @Override
  public void render(Graphics2D g2) {
    super.render(g2);
  }
}
