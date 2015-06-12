#include <boost/random.hpp>
#include <ctime>

using namespace boost;

double SampleNormal (double mean, double sigma)
{
    static mt19937 rng(static_cast<unsigned> (std::time(0)));

    normal_distribution<double> norm_dist(mean, sigma);

    variate_generator<mt19937&, normal_distribution<double> normal_sampler(rng, norm_dist);

    return normal_sampler();
}
